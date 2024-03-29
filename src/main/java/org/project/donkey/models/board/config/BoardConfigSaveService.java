package org.project.donkey.models.board.config;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.donkey.api.admin.BoardConfigForm;
import org.project.donkey.commons.Utils;
import org.project.donkey.commons.constants.BoardAuthority;
import org.project.donkey.entities.Board;
import org.project.donkey.repositories.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {
    private final BoardRepository boardRepository;
    private final Utils utils;

    public void save(BoardConfigForm form) {

        String bId = form.getBId();
        String mode = Objects.requireNonNullElse(form.getMode(), "add");
        Board board = null;
        if (mode.equals("edit") && StringUtils.hasText(bId)) {
            board = boardRepository.findById(bId).orElseThrow(BoardNotFoundException::new);
        } else { // 추가
            board = new Board();
            board.setBId(bId);
        }

        board.setBName(form.getBName());
        board.setActive(form.isActive());
        board.setAuthority(BoardAuthority.valueOf(form.getAuthority()));

        boardRepository.saveAndFlush(board);
    }

    /**
     * 게시판 설정 목록 수정
     *
     * @param idxes
     */
    public void update(List<Integer> idxes) throws BadRequestException {
        if (idxes == null || idxes.isEmpty()) {
            throw new BadRequestException(Utils.getMessage("수정할 게시판을 선택하세요.","validation"));
        }

        for (int idx : idxes) {
            String bId = utils.getParam("bId_" + idx);
            Board board = boardRepository.findById(bId).orElse(null);
            if (board == null) continue;

            String bName = utils.getParam("bName_" + idx);
            boolean active = Boolean.parseBoolean(utils.getParam("active_" + idx));
            BoardAuthority authority =
                    BoardAuthority.valueOf(utils.getParam("authority_" + idx));

            board.setBName(bName);
            board.setActive(active);
            board.setAuthority(authority);
        }

        boardRepository.flush();
    }
}
