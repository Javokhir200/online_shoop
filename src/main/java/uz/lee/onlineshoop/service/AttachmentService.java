package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
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
    public Attachment saveAttachment(Attachment attachment) {
        if(attachment == null) {
            return null;
        }
        return repository.save(attachment);
    }
}
