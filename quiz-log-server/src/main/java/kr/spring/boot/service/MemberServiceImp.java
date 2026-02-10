package kr.spring.boot.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberVO getMember(String mb_id) {
		return memberDao.selectMember(mb_id);
	}

	@Override
	public boolean signup(SignupDTO user) {
		if(user == null) {
			return false;
		}
		if (user.getMb_id() == null || user.getMb_id().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_id(), "^\\w{6,20}$")) {
			return false;
		}
		if (user.getMb_pw() == null || user.getMb_pw().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_pw(), "^[a-zA-Z0-9!@#$]{6,20}$")) {
			return false;
		}
		user.setMb_pw(passwordEncoder.encode(user.getMb_pw()));
		return memberDao.signup(user);
	}
	
	private boolean checkRegex(String str, String regex) {
		if (str != null && Pattern.matches(regex, str)) {
			return true;
		}
		return false;
	} // 정규 표현식 검사 메소드 (맞으면 참, 틀리면 거짓)
	
}
