package org.project.donkey.api.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.donkey.commons.ListData;
import org.project.donkey.entities.Board;
import org.project.donkey.models.board.config.BoardConfigDeleteService;
import org.project.donkey.models.board.config.BoardConfigInfoService;
import org.project.donkey.models.board.config.BoardConfigSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/admin/board")
@RequiredArgsConstructor
public class BoardController{

    private final HttpServletRequest request;
    private final BoardConfigSaveService saveService;
    private final BoardConfigInfoService infoService;
    private final BoardConfigDeleteService deleteService;

    @GetMapping()
    public ResponseEntity<ListData<Board>> list(@PathVariable BoardSearch search) {

        ListData<Board> data = infoService.getList(search);


        return ResponseEntity.ok(data);
    }

    @PatchMapping
    public ResponseEntity updateList(@RequestParam(name="idx", required = false) List<Integer> idxes) throws BadRequestException {

        saveService.update(idxes);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteList(@RequestParam(name="idx", required = false) List<Integer> idxes) throws BadRequestException {

        deleteService.delete(idxes);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/add")
    public ResponseEntity<BoardConfigForm> register(@ModelAttribute BoardConfigForm form) {
        saveService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/edit/{bId}")
    public ResponseEntity<BoardConfigForm> update(@PathVariable("bId") String bId, @Valid @RequestBody BoardConfigForm form) {

        saveService.save(form);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@Valid @RequestBody BoardConfigForm form, Errors errors) {
        String mode = Objects.requireNonNullElse(form.getMode(), "add");
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        saveService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
