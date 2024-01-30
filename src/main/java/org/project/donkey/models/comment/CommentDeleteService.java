package org.project.donkey.models.comment;

import lombok.RequiredArgsConstructor;
import org.project.donkey.entities.BoardData;
import org.project.donkey.entities.CommentData;
import org.project.donkey.repositories.CommentDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {
    private final CommentInfoService infoService;
    private final CommentDataRepository repository;

    public BoardData delete(Long seq) {

        infoService.isMine(seq);

        CommentData commentData = infoService.get(seq);
        BoardData boardData = commentData.getBoardData();

        repository.delete(commentData);
        repository.flush();

        // 총 댓글 수 업데이트
        Long boardDataSeq = boardData.getSeq();
        infoService.updateCommentCnt(boardDataSeq);

        return boardData;
    }
}