package co.paradocs.simpleapi;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private MessageRepository messageRepository;

  @Autowired
  public MessageController(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @GetMapping("/messages")
  @ResponseBody
  public List<Message> getMessages() {
    return this.messageRepository.findAll();
  }

  @PostMapping("/messages")
  public Message createMessage(@RequestBody Message message){
    return messageRepository.save(message);
  }

  @GetMapping("/messages/{id}")
  @ResponseBody
  public Message getMessages(@PathVariable Long id) {
    Message message = null;

    Optional<Message> optionalMessage = this.messageRepository.findById(id);
    if (optionalMessage.isPresent()){
      message = optionalMessage.get();
    }
    return message;
  }

}
