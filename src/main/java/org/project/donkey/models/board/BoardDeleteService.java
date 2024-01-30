package org.project.donkey.models.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.donkey.entities.BoardData;
import org.project.donkey.models.file.FileDeleteService;
import org.project.donkey.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardInfoService infoService;
    private final BoardDataRepository repository;
    private final FileDeleteService fileDeleteService;

    public void delete(Long seq) {
        BoardData data = infoService.get(seq);
        String gid = data.getGid();

        // 파일 삭제
        fileDeleteService.deleteByGid(gid);

        // 게시글 삭제
        repository.delete(data);

        repository.flush();

    }
}
