package service;

import dto.BookDTO;
import entity.Book;
import lombok.RequiredArgsConstructor;
import mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks(){
        return bookMapper.toResponseDTOList(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public BookDTO getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toResponseDTO(book);
    }

    @Transactional
    public BookDTO createBook(BookDTO request){
        Book book = bookMapper.toEntity(request);
        Book saved = bookRepository.save(book);
        return bookMapper.toResponseDTO(saved);
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO request){
        Book book = bookMapper.toEntity(request);
        Book saved = bookRepository.save(book);
        return  bookMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteBook(Long id){
        Book book = bookRepository.getReferenceById(id);
        bookRepository.deleteById(book.getId());
    }
}
