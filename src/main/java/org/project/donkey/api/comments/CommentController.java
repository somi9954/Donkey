package org.project.donkey.api.comments;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.donkey.commons.Utils;
import org.project.donkey.entities.BoardData;
import org.project.donkey.entities.CommentData;
import org.project.donkey.models.board.RequiredPasswordCheckException;
import org.project.donkey.models.comment.CommentDeleteService;
import org.project.donkey.models.comment.CommentInfoService;
import org.project.donkey.models.comment.CommentSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController{

    private final CommentSaveService saveService;
    private final CommentDeleteService deleteService;
    private final CommentInfoService infoService;

    private final Utils utils;

    @GetMapping("/update/{seq}")
    public ResponseEntity<CommentForm> update(@PathVariable("seq") Long seq) {

        CommentForm form = infoService.getForm(seq);

        return ResponseEntity.ok(form);
    }

    @PostMapping("/save")
    public ResponseEntity<CommentData> saveComment(@Valid @RequestBody CommentForm form, Errors errors) {
        saveService.save(form, errors);

        if (errors.hasErrors()) {
            Map<String, List<String>> messages = Utils.getMessages(errors);
            String message = (new ArrayList<List<String>>(messages.values())).get(0).get(0);
            throw new BadRequestException(message);
        }

        Long seq = form.getSeq();
        if (seq == null) {
            return ResponseEntity.ok().build();
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{seq}")
                    .buildAndExpand(seq)
                    .toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @RequestMapping("/delete/{seq}")
    public ResponseEntity<CommentData> delete(@PathVariable("seq") Long seq) {

        deleteService.delete(seq);

      return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RequiredPasswordCheckException.class)
    public String guestPassword() {
        return utils.tpl("board/password");
    }
}
