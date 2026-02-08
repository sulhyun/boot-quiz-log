package kr.spring.boot.service;

import kr.spring.boot.model.vo.MemberVO;

public interface MemberService {

	MemberVO getMember(String mb_id);

}
