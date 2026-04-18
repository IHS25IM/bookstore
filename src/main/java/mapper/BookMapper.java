package mapper;

import dto.BookDTO;
import entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {
    public Book toEntity(BookDTO dto){
        Book entity = new Book();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public BookDTO toResponseDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        return dto;
    }

    public List<BookDTO> toResponseDTOList(List<Book> books){
        return books.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
