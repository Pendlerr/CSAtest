package cSAtest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSAtest {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		boolean flag=true;
		String sno,sname;
		int age;
		//���ݳ�ʼ��
		Action act;
		CourseTable ct=new CourseTable();
		ct.Add("0","����",28,"���ݿ�","java","�ߵ���ѧ");
		ct.Add("1","����",21,"���Դ���","c++","a");
		ct.Add("2","����",28,"���ݿ�","java","�ߵ���ѧ","a");
		
		while(flag) {
			System.out.println("������������������������������������������������������������������������������������������������");
			System.out.println("���ĸ�����л��(���룺ѧ����Ϣ��/ѧ��ѡ�α�)");
			String table=in.next();
			if(table.equals("ѧ����Ϣ��")) {
				StudentTable stut=ct;//ct��ת��
				act=stut;
			}
			else if(table.equals("ѧ��ѡ�α�")) {
				act=ct;
			}
			else {
				System.out.println("����ı����������롣");
				continue;
			}
			System.out.println("����1��2��3��4��5��0��ʵ��\nչʾ������ɾ���ġ��顢�˳�����:");//չʾ������ɾ���ġ��顢�˳�
			int comm=in.nextInt();
			switch (comm){
			case 1:
				if(table.equals("ѧ����Ϣ��"))
					act.show(0,act.getsize());
				else if(table.equals("ѧ��ѡ�α�"))
					ct.show();
				flag=true;
				break;
			case 2:
				System.out.println("����Ҫ���ӵ���Ϣ��");
				sno=in.next();
				sname=in.next();
				age=in.nextInt();
				String []course=in.nextLine().split(" ");
				act.Add(sno,sname,age,course);
				flag=true;
				break;
			case 3:
				act.delete();
				flag=true;
				break;
			case 4:
				System.out.println("����Ҫ�޸ĵ���Ϣ��");
				sno=in.next();
				sname=in.next();
				age=in.nextInt();
				course=in.nextLine().split(" ");
				act.update(sno,sname,age,course);
				flag=true;
				break;
			case 5:
				System.out.println("����Ҫ���ҵ���Ϣ��");
				if(table.equals("ѧ����Ϣ��")) {
					sno=in.next();
					act.show(act.find(sno),1);
				}
				else {
					String nc=in.next();
					ct.findc(nc);
				}
				flag=true;
				break;
			case 0:
				flag=false;
				break;
			default:
				System.out.println("����������������롣");
				flag=true;
				break;
			}
		}
	}
}

interface Action{
	void Add(String sno, String sname,int age,String ...course);//
	void delete();
	void update(String sno, String sname, int age, String... course);
	int find(String sno);
	void show(int x,int len);
	int getsize();
}

class Student{
	String sno;
	String sname;
	int age;
	List<String> course=new ArrayList<String>();
	public Student(String sno, String sname, int age, String ...Course) {
		this.sno = sno;
		this.sname = sname;
		this.age = age;
		for(String c:Course) {
			if(c!="")
			course.add(c);
		}
	}
}

class StudentTable implements Action{
	List<Student> stu=new ArrayList<Student>();
	
	public void Add(String sno, String sname,int age,String ...course) {
		boolean flag = true;
		for (Student x : stu) {
			if (x.sno.equals(sno)) {
				for (String c : course)
					if(c!=""&&x.course.indexOf(c)==-1)
					x.course.add(c);
				flag = false;
			}
		}
		Student newstu=new Student(sno,sname,age,course);
		if(flag)
		stu.add(newstu);
	}

	public void delete() {
		Scanner in=new Scanner(System.in);
		String sno,c;
		System.out.println("Ҫɾ���˿γ�����ѡ����Ϣ������ɾ��ĳѧ��ѡ�μ�¼������ɾ��ĳѧ��ȫ����Ϣ��");
		int tag=in.nextInt();
		boolean flag=true;
		switch (tag){
		case 1:
			System.out.println("����Ҫɾ���Ŀγ�����");
			c=in.next();
			for (Student x : stu) {
				for (int c0 = 0; c0 < x.course.size(); c0++)
					if (x.course.get(c0).equals(c)) {
						x.course.remove(c0);
						flag = false;
					}
			}
			break;
		case 2:
			System.out.println("����Ҫɾ����ѧ��ѧ�ż��γ�����");
			sno=in.next();
			c=in.next();
			for (Student x : stu) {
				if (x.sno.equals(sno)) {
					x.course.remove(c);
					flag = false;
					break;
				}
			}
			break;
		case 3:
			System.out.println("����Ҫɾ����ѧ��ѧ��");
			sno=in.next();
			for (int x=0;x<stu.size();x++) {
				if (stu.get(x).sno.equals(sno)) {
					stu.remove(x);
					flag = false;
					break;
				}
			}
			break;
		}
		if(flag) {
			System.out.println("����Ϣ�������Ƿ���������");			
		}
	}

	public void update(String sno, String sname, int age, String... course) {
		int p=find(sno);
		if(p>=0) {
			stu.get(p).sname=sname;
			stu.get(p).age=age;
			stu.get(p).sname=sname;
			stu.get(p).course.clear();
			this.Add(sno,sname,age,course);
		}
		else {
			System.out.println("����Ϣ�������Ƿ���������");
		}
	}
	public int find(String sno) {
		for (int x=0;x<stu.size();x++) {
			if (stu.get(x).sno.equals(sno)) {
				return x;
			}
		}
		return -1;
	}
	public void show(int x,int len){
		System.out.println("����ѧ����Ϣ����");
		for(int i=x;i<x+len;i++) {
			System.out.print(stu.get(i).sno+'\t'+stu.get(i).sname+'\t'+stu.get(i).age+'\t');
			if(stu.get(i).course.size()>0) {
				System.out.println(stu.get(i).course.get(0)+"\t");
			}
			else {
				System.out.println();
			}
			for(int j=1;j<stu.get(i).course.size();j++) {
				System.out.println("\t\t\t"+stu.get(i).course.get(j)+"\t");
			}
		}
	}
	public int getsize(){
		return stu.size();
	}
}

class CourseTable extends StudentTable{
	List<String> cl=new ArrayList<String>();
	CourseTable(){
		for (Student x : stu) {
			for(String c:x.course) {
				if(!cl.contains((Object)c))
					cl.add(c);
			}
		}
	}
	public void show(){
		System.out.println("�����γ���Ϣ����\n");
		List<String> cl=new ArrayList<String>();
		for (Student x : stu) {
			for(String c:x.course) {
				if(!cl.contains((Object)c))
					cl.add(c);
			}
		}
		for (String c : cl) {
			System.out.print(c);
			for (Student x : stu) {
					if(x.course.contains((Object)c))
						System.out.println('\t'+x.sno+'\t'+x.sname+'\t'+x.age);
			}
			System.out.println();
		}
	}
	void setcl() {
		for (Student x : stu) {
			for(String c:x.course) {
				if(!cl.contains((Object)c))
					cl.add(c);
			}
		}
	}
	public void findc(String c) {
		setcl();
		for (String cf : cl) {
			if (cf.equals(c)) {
				System.out.print(c);
				for (Student x : stu) {
					if (x.course.contains((Object) c))
						System.out.println('\t' + x.sno + '\t' + x.sname + '\t' + x.age);
				}
				break;
			}
		}
	}
}