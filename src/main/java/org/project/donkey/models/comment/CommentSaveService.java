package org.project.donkey.models.comment;

import lombok.RequiredArgsConstructor;
import org.project.donkey.api.comments.CommentForm;
import org.project.donkey.api.comments.CommentFormValidator;
import org.project.donkey.configs.jwt.CustomJwtFilter;
import org.project.donkey.entities.BoardData;
import org.project.donkey.entities.CommentData;
import org.project.donkey.models.board.BoardDataNotFoundException;
import org.project.donkey.repositories.BoardDataRepository;
import org.project.donkey.repositories.CommentDataRepository;
import org.project.donkey.models.comment.CommentInfoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class CommentSaveService {
    private final CommentDataRepository commentDataRepository;
    private final BoardDataRepository boardDataRepository;
    private final CommentInfoService commentInfoService;

    private final CommentFormValidator validator;
    private final CustomJwtFilter customJwtFilter;
    private final PasswordEncoder encoder;

    public void save(CommentForm form, Errors errors) {
        validator.validate(form, errors);
        if (errors.hasErrors()) {
            return;
        }

        CommentData commentData = null;
        Long seq = form.getSeq();
        System.out.println(form);
        if (seq == null) { // 추가 - 게시글 번호, 회원 번호
            commentData = new CommentData();
            Long boardDataSeq = form.getBoardDataSeq();
            BoardData boardData = boardDataRepository.findById(boardDataSeq).orElseThrow(BoardDataNotFoundException::new);
            commentData.setBoardData(boardData);
            commentData.setMember(customJwtFilter.getMember());

        } else { // 수정
            commentData = commentDataRepository.findById(seq).orElseThrow(CommentNotFoundException::new);
        }

        commentData.setPoster(form.getPoster());
        commentData.setContent(form.getContent());

        String guestPw = form.getGuestPw();
        if (StringUtils.hasText(guestPw)) {
            commentData.setGuestPw(encoder.encode(guestPw));
        }

        save(commentData);

    }

    public void save(CommentData comment) {

        commentDataRepository.saveAndFlush(comment);

        // 총 댓글 갯수 업데이트
        Long boardDataSeq = comment.getBoardData().getSeq();
        commentInfoService.updateCommentCnt(boardDataSeq);
    }
}