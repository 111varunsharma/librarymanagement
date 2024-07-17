package com.bansira.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {

	private Map<String, List<Book>> departments;

	public Library() {
		this.departments = new HashMap<>();
	}

	public void addBook(Book book) {
		String department = book.getDepartment();
		if (!departments.containsKey(department)) {
			departments.put(department, new ArrayList<>());
		}

		for (List<Book> books : departments.values()) {
			for (Book b : books) {
				if (b.getISBN().equals(book.getISBN())) {
					System.out.println("Book with ISBN " + book.getISBN() + " already exists in another department.");
					return;
				}
			}
		}

		departments.get(department).add(book);
	}

	public void removeBook(String ISBN) {
		for (List<Book> books : departments.values()) {
			books.removeIf(book -> book.getISBN().equals(ISBN));
		}
	}

	public List<Book> findBookByTitle(String title) {
		return departments.values().stream().flatMap(List::stream)
				.filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
	}

	public List<Book> findBookByAuthor(String author) {
		return departments.values().stream().flatMap(List::stream)
				.filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
	}

	public List<Book> listAllBooks() {
		return departments.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}

	public List<Book> listAvailableBooks() {
		return departments.values().stream().flatMap(List::stream).filter(Book::isAvailability)
				.collect(Collectors.toList());
	}
}
