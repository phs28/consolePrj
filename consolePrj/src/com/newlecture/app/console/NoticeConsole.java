package com.newlecture.app.console;

import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
	}

	public void printNoticeList() throws Exception {
		
		List<Notice> list = service.getList(2);
		
		
		System.out.println("--------------------------------------------");
		System.out.printf("\t    <공지사항> 총%d 게시글\n", 12);
		System.out.println("--------------------------------------------");
		for(Notice n : list) { 
			System.out.printf("%d. %s / %s / %s\n", 
					n.getId(), 
					n.getTitle(), 						  
					n.getWirterid(), 		
					n.getRegDate());
		}
		System.out.println("--------------------------------------------");
		System.out.printf("\t\t%d/%d pages\n", 1, 2);
	}

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.종료");
		String menu_ = scan.nextLine();
		int menu = Integer.parseInt(menu_);
		
		return menu;
	}

	public void moverPreList() {
		
	}

	public void moverNextlist() {
		
	}
	
}
