package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.AttachmentDto;
import uz.lee.onlineshoop.entity.Attachment;
import uz.lee.onlineshoop.repository.AttachmentRepository;

import java.util.List;

@Service
public class AttachmentService {
    private final AttachmentRepository repository;

    public AttachmentService(AttachmentRepository repository) {
        this.repository = repository;
    }
    public List<Attachment> getAllAttachment() {
        return repository.findAll();
    }
    public AttachmentDto saveAttachment(AttachmentDto attachment) {
        if(attachment == null) {
            return null;
        }
        Attachment attachment1 = getAttachment(attachment);
        repository.save(attachment1);
        return attachment;
    }
    public AttachmentDto getById(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Attachment not found!"));
        return new AttachmentDto(
                attachment.getId(),
                attachment.getOriginalName(),
                attachment.getDescription(),
                attachment.getSubmittedName(),
                attachment.getFileUrl()
        );
    }
    private static Attachment getAttachment(AttachmentDto dto) {
        return new Attachment(
                dto.getId(),
                dto.getOriginalName(),
                dto.getDescription(),
                dto.getSubmittedName(),
                dto.getFileUrl()
        );
    }
}
