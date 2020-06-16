package com.koreait.app.board.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class FilesDAO {
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public FilesDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	public boolean insertFiles(MultipartRequest multi, int board_num) {
		HashMap<String, Object> fileMap = new HashMap<>();
		Enumeration<String> files = multi.getFileNames();
//		System.out.println(files);
		boolean check = true;
		
		fileMap.put("board_num", board_num);
		
		while(files.hasMoreElements()) {
			String data = files.nextElement();
//			System.out.println(data);
			if(multi.getFilesystemName(data) == null) {continue;}
			fileMap.put("file_name", multi.getFilesystemName(data));
			if(sqlsession.insert("Files.insertFile", fileMap) != 1) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	public List<FilesBean> getDetail(int board_num) {
		List<FilesBean> filesBeanList = sqlsession.selectList("Files.getDetail", board_num);
		return filesBeanList;
	}
}










