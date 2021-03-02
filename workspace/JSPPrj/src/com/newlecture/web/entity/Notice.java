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
	//�����͸� ä������� ���͸� �غ����ٵ� �װź��ٴ� �����ڸ� �غ��ؼ� �����ڷ� ä����
	//��� ������ source=> generate construct using Field
	//�����ε� �����ڰ� ������ �׷��� �⺻�����ڸ� �����Ұ����� �������� �Ǵ��ؾ��� �����Ϸ��� �ȸ�����ִϱ� �⺻������ �߰��ϱ����� ctrl space������ �⺻�����ڸ� �����Ҽ��ִ�.
	
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
	//source=> Generate Getters and Setters ���� ��μ���
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
	//���߿� �������ִ� ���� �׽�Ʈ�غ�������� toString�� �������̵��ؼ� �ٲ��ָ�ȴ�
	//source=> Generate toString ���� ��μ���
	//�÷����� ����ϴ� ������� �������̵� ���ش�. ���߿� ����ϰ� ����غ�������
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regdate=" + regdate + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
}
