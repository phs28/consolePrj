package com.newlecture.app.console;

import java.util.List;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialArray;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private int count;
	private String searchWord;
	private String searchField;
	
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0;
		searchWord = "";
		searchField = "title";
	}

	public void printNoticeList() throws Exception {
		
		List<Notice> list = service.getList(page, searchField, searchWord);
		count = service.getCount();
		int lastPage = count/10;
		
		System.out.println("--------------------------------------------");
		System.out.printf("\t    <공지사항> 총%d 게시글\n", count);
		System.out.println("--------------------------------------------");
		for(Notice n : list) { 
			System.out.printf("%d. %s / %s / %s\n", 
					n.getId(),
					n.getTitle(),					  
					n.getWirterid(),	
					n.getRegDate());
		}
		System.out.println("--------------------------------------------");
		System.out.printf("\t\t%d/%d pages\n", page, lastPage);
	}

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		String menu_ = scan.nextLine();
		int menu = Integer.parseInt(menu_);
		
		return menu;
	}

	public void moverPreList() {
		if(page == 1) {
			System.out.println("------------------");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("------------------");
			return;
		}
		page--;
	}

	public void moverNextlist() throws Exception {
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10>0 ? lastPage+1:lastPage;
		
		if(page == lastPage) {
			System.out.println("------------------");
			System.out.println("다음 페이지가 없습니다.");
			System.out.println("------------------");
			return;
		}
		page++;
	}

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 범주(title/content/writerId)중에 입력하세요");
		System.out.print("검색 > ");
		searchField = scan.nextLine();
		
		System.out.print("검색어 > ");
		searchWord = scan.nextLine();
		
	}
	
}
