package org.project.donkey.models.board.config;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.donkey.commons.Utils;
import org.project.donkey.entities.Board;
import org.project.donkey.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardRepository repository;
    private final Utils utils;

    /**
     * 게시판 설정 삭제
     *
     * @param bId
     */
    public void delete(String bId) throws BoardNotFoundException {
        Board board = repository.findById(bId).orElseThrow(BoardNotFoundException::new);

        repository.delete(board);
        repository.flush();
    }

    /**
     * 목록에서 일괄 삭제
     *
     * @param idxes
     */
    public void delete(List<Integer> idxes) throws BadRequestException {
        if (idxes == null || idxes.isEmpty()) {
            throw new BadRequestException(Utils.getMessage("삭제할 게시판을 선택하세요.","error"));
        }


        for (int idx : idxes) {
            String bId = utils.getParam("bId_" + idx);
            Board board = repository.findById(bId).orElse(null);
            if (board == null) continue;

            repository.delete(board);
        }

        repository.flush();
    }
}
