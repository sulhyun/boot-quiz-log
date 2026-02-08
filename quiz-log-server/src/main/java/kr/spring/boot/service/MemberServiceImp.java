package kr.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public MemberVO getMember(String mb_id) {
		return memberDao.selectMember(mb_id);
	}
	
}
