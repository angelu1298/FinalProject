package com.burn.fat.board.fboard.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.fboard.dao.FBoardService;
import com.burn.fat.board.fboard.model.FbbsBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class FbbsAction {

	@Autowired
	private FBoardService fbbsService;

	private String saveFolder = "C:/Users/�����/git/FinalProject/src/main/webapp/resource/upload"; 
	//�������� ���
																								

	/* �ڷ�� �Է��� */
	@RequestMapping(value = "/boardWrite.brn")
	public String fbbs_write() {
		return "html_community/boarWrite"; // html_community����
	}
	
    @RequestMapping(value="/boardWrite_ok.brn",
          method=RequestMethod.POST)
    public ModelAndView bbs_write_ok(
          HttpServletRequest request,
          HttpServletResponse response) throws Exception{
       
       FbbsBean fbbsbean=new FbbsBean();      
       int fileSize=5*1024*1024; //�������� �ִ� ���ε� ũ��   
       
       MultipartRequest multi=null;
       multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
       
       String f_sj = multi.getParameter("bbs_subject").trim();
       String f_ct = multi.getParameter("bbs_content");
       
       
       File UpFile=multi.getFile("bbs_file");
       if(UpFile != null){//÷���� ���������� �ִٸ�
          String fileName=UpFile.getName();//�������ϸ� ����
          System.out.println("fileName = " + fileName);
          Calendar c=Calendar.getInstance();
          int year=c.get(Calendar.YEAR);      //���� �⵵ ���մϴ�.
          int month=c.get(Calendar.MONTH)+1;  //���� �� ���մϴ�.
          int date=c.get(Calendar.DATE);      //���� �� ���մϴ�.
          
             String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
             System.out.println("homedir = " + homedir);
             //upload���� �Ʒ��� ���� �ø� ��¥�� ���� �����մϴ�.
             File path1=new File(homedir);
             if(!(path1.exists())){
                path1.mkdir();//���ο� ������ ����
             }
             //������ ���մϴ�.
             Random r=new Random();
             int random=r.nextInt(100000000);
             
             /****Ȯ���� ���ϱ� ���� ****/
          int index = fileName.lastIndexOf(".");
          //���ڿ����� Ư�� ���ڿ��� ��ġ ��(index)�� ��ȯ�Ѵ�.
          //indexOf�� ó�� �߰ߵǴ� ���ڿ��� ���� index�� ��ȯ�ϴ� �ݸ�,
          //lastIndexOf�� ���������� �߰ߵǴ� ���ڿ��� index�� ��ȯ�մϴ�.
          //(���ϸ� ���� ������ ���� ��� �� �������� �߰ߵǴ� ���ڿ��� ��ġ�� �����մϴ�.)
          System.out.println("index = " +  index);
          
          String fileExtension = fileName.substring(index + 1);
          System.out.println("fileExtension = " +  fileExtension);
          /****Ȯ���� ���ϱ� �� ***/
          
          //���ο� ���ϸ��� ����
          String refileName="bbs"+year+month+date+random+"."+
                fileExtension;
          System.out.println("refileName = " + refileName);
          
           //����Ŭ ��� ����� ���ڵ� ��
             String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
             System.out.println("fileDBName = " + fileDBName);
            
             //���ϸ� �����մϴ�.
             UpFile.renameTo(new File(homedir+"/"+refileName));
             System.out.println("homedir / refileName  = " + homedir+"/"+
             refileName);
             
             //�ٲ� ���ϸ�����  ����
             fbbsbean.setF_fl(fileDBName);
       }
       fbbsbean.setF_sj(f_sj);
       fbbsbean.setF_ct(f_ct);
       
       this.fbbsService.insertBbs(fbbsbean); //����޼��� ȣ��
       
       response.sendRedirect("boardList.brn");
       return null;
    }
    
    /* �ڷ�� ��� */
    @RequestMapping(value="/boardList.brn")
    public ModelAndView bbs_list(HttpServletRequest request,
          HttpServletResponse response) throws Exception{
           
       int page = 1;
        //int limit=10; // �� ȭ�鿡 ����� ���ڵ� ����
        int limit=3;   //��Ϻ��� �ʱⰪ
        HttpSession session = request.getSession();
        
        if(request.getParameter("page") != null){
                page=Integer.parseInt(request.getParameter("page"));
       }
        
        //�߰�
        //������ ������ limit�� �ִ��� üũ
        if(session.getAttribute("limit")!=null) {
           limit = (Integer)session.getAttribute("limit");
        }
        //����� limit�� �ִ��� üũ
        if(request.getParameter("limit")!=null) {
           limit=Integer.parseInt(request.getParameter("limit"));
           session.setAttribute("limit", limit);
        }
        
        System.out.println("limit = " + limit);
        System.out.println("page = " + page);
        //������� �߰�
        
       //�� ����Ʈ ���� �޾ƿ�.               
       int listcount=this.fbbsService.getListCount(); //�� ����Ʈ ���� �޾ƿ�
             
       //�� ������ ��
       int maxpage = (listcount+limit-1)/limit;
                    
       //���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
       int startpage = ((page-1) / 10) * 10 + 1;
                    
       //���� �������� ������ ������ ������ ��(10, 20, 30 ��...)
       int endpage = startpage + 10 -1;
                      
       if (endpage > maxpage) endpage= maxpage;
                      
       if (endpage < page) page = endpage;  
       
       Map m = new HashMap();
       m.put("page", page);
       m.put("limit", limit);
       //����Ʈ �޾ƿ�
       List<FbbsBean> bbslist = fbbsService.getBbsList(m);//page,limit�� ����
    /*
       request.setAttribute("page", page);   //���� ������ �� 
       request.setAttribute("maxpage", page);   //���� ������ �� 
       request.setAttribute("startpage", page);   //���� ������ �� 
       request.setAttribute("endpage", page);   //���� ������ �� 
       request.setAttribute("listcount", page);   //���� ������ �� 
       request.setAttribute("bbslist", page);   //���� ������ �� 
       request.setAttribute("list", page);   //���� ������ �� 
    */
     ModelAndView model=new ModelAndView("html_community/boarList");
     model.addObject("page", page);
     model.addObject("maxpage", maxpage);
     model.addObject("startpage", startpage);
     model.addObject("endpage", endpage);
     model.addObject("listcount", listcount);
     model.addObject("bbslist", bbslist);
     model.addObject("limit", limit);
     
     return model;      
       
    }
    
 
   
 }
