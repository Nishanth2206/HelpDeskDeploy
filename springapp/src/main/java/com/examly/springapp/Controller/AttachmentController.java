package com.examly.springapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Attachment;
import com.examly.springapp.service.AttachmentService;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping
    public List<Attachment> getAllAttachments() {
        return attachmentService.getAllAttachments();
    }

    @GetMapping("/{id}")
    public Optional<Attachment> getAttachmentById(@PathVariable("id") Long attachmentId) {
        return attachmentService.getAttachmentById(attachmentId);
    }

    @PostMapping
    public Attachment createAttachment(@RequestBody Attachment attachment) {
        return attachmentService.createAttachment(attachment);
    }

    @PutMapping("/{id}")
    public Attachment updateAttachment(@PathVariable("id") Long attachmentId, @RequestBody Attachment attachment) {
        return attachmentService.updateAttachment(attachmentId, attachment);
    }

    @DeleteMapping("/{id}")
    public String deleteAttachment(@PathVariable("id") Long attachmentId) {
        boolean isDeleted = attachmentService.deleteAttachment(attachmentId);
        return isDeleted ? "Attachment deleted successfully!" : "Attachment not found!";
    }
}

