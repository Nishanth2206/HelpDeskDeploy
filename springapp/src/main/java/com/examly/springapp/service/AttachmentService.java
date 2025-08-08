package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Attachment;
import com.examly.springapp.repository.AttachmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    public Optional<Attachment> getAttachmentById(Long attachmentId) {
        return attachmentRepository.findById(attachmentId);
    }

    public Attachment createAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    public Attachment updateAttachment(Long attachmentId, Attachment updatedAttachment) {
        return attachmentRepository.findById(attachmentId).map(attachment -> {
            if (updatedAttachment.getFileUrl() != null) {
                attachment.setFileUrl(updatedAttachment.getFileUrl());
            }
            if (updatedAttachment.getFileName() != null) {
                attachment.setFileName(updatedAttachment.getFileName());
            }
            return attachmentRepository.save(attachment);
        }).orElse(null);
    }

    public boolean deleteAttachment(Long attachmentId) {
        if (attachmentRepository.existsById(attachmentId)) {
            attachmentRepository.deleteById(attachmentId);
            return true;
        }
        return false;
    }
}
