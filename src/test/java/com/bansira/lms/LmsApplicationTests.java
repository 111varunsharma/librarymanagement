package com.bansira.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bansira.lms.service.Book;
import com.bansira.lms.service.Library;

@SpringBootTest
class LmsApplicationTests {

	private Library library;
	private Book book1;
	private Book book2;
	private Book book3;

	@BeforeEach
	public void setUp() {
		library = new Library();
		book1 = new Book("Effective Java", "Joshua Bloch", "123456789", "Programming", 2018, "Computer Science", true);
		book2 = new Book("Java Concurrency in Practice", "Brian Goetz", "987654321", "Concurrency", 2006,
				"Software Engineering", true);
		book3 = new Book("Clean Code", "Robert C. Martin", "111111111", "Programming", 2008, "Software Engineering",
				false);
	}

	@Test
	public void testAddBook() {
		library.addBook(book1);
		library.addBook(book2);
		assertEquals(2, library.listAllBooks().size());
	}

	@Test
	public void testAddDuplicateBookInSameDepartment() {
		library.addBook(book1);
		library.addBook(new Book("Another Book", "Another Author", "123456789", "Different Genre", 2020,
				"Computer Science", true));
		assertEquals(1, library.listAllBooks().size());
	}

	@Test
	public void testAddDuplicateBookInDifferentDepartment() {
		library.addBook(book1);
		library.addBook(new Book("Another Book", "Another Author", "123456789", "Different Genre", 2020,
				"Software Engineering", true));
		assertEquals(1, library.listAllBooks().size());
	}

	@Test
	public void testRemoveBook() {
		library.addBook(book1);
		library.addBook(book2);
		library.removeBook("123456789");
		assertEquals(1, library.listAllBooks().size());
	}

	@Test
	public void testFindBookByTitle() {
		library.addBook(book1);
		library.addBook(book2);
		List<Book> books = library.findBookByTitle("Effective Java");
		assertEquals(1, books.size());
		assertEquals("Joshua Bloch", books.get(0).getAuthor());
	}

	@Test
	public void testFindBookByAuthor() {
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		List<Book> books = library.findBookByAuthor("Robert C. Martin");
		assertEquals(1, books.size());
		assertEquals("Clean Code", books.get(0).getTitle());
	}

	@Test
	public void testListAllBooks() {
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		assertEquals(3, library.listAllBooks().size());
	}

	@Test
	public void testListAvailableBooks() {
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		List<Book> books = library.listAvailableBooks();
		assertEquals(2, books.size());
	}

}
