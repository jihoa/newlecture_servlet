package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String writerId;
	private Date regdate; 
	private String hit;
	private String files;
	private String content;
	//데이터를 채우기위해 세터를 준비할텐데 그거보다는 생성자를 준비해서 생성자로 채우자
	//마운스 오른쪽 source=> generate construct using Field
	//오버로드 생성자가 생성됨 그러면 기본생성자를 생성할것인지 말것인지 판단해야함 컴파일러가 안만들어주니까 기본생성자 추가하기위해 ctrl space누르면 기본생성자를 생성할수있다.
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	public Notice(int id, String title, String writerId, Date regdate, String hit, String files, String content) {
//		super();
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}
	//source=> Generate Getters and Setters 에서 모두선택
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}  
	//나중에 가지고있는 값을 테스트해보고싶으면 toString을 오버라이드해서 바꿔주면된다
	//source=> Generate toString 에서 모두선택
	//컬럼들을 출력하는 방식으로 오버라이드 해준다. 나중에 요긴하게 출력해볼수있음
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regdate=" + regdate + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
}
