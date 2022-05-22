package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * 로그인
	 * 
	 * @throws Exception
	 */
	@PostMapping("/login")
	public String login(String id, String password, HttpSession session) throws Exception {
		User dto = service.login(id, password);

		if (dto != null) {
			session.setAttribute("loginInfo", dto);
			return "redirect:/";
		} else {
			return "redirect:/";
		}

	}

	/** 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		if (session.getAttribute("loginInfo") != null) {
			session.removeAttribute("loginInfo");
		}

		session.invalidate();

		return "redirect:/";
	}

	/**
	 * 회원 가입
	 * 
	 * @throws Exception
	 */
	@PostMapping("/signUp")
	public String signUp(User user, Model model, RedirectAttributes redirectAttributes) throws Exception {
		// 여기서 유사성 체크
		Set<String> idSet = new HashSet<>();
		String id = user.getId();
		String password = user.getPassword();

		// 아이디에서 연속된 3글자를 얻어와서 set에 저장(중복제거를 위해)
		for (int i = 0; i < id.length() - 2; i++) {
			idSet.add(id.substring(i, i + 3));
		}

		Iterator<String> it = idSet.iterator();
		while (it.hasNext()) {
			// set에 있는 원소 하나씩 빼서 비밀번호 문자열과 비교
			// 만약, 비밀번호 문자열이 set 원소를 포함한다면, alert을 통해 경고창 띄우기

			if(checkSimilarity(it.next(), password)) {
				// 유사도가 높으면
				
				model.addAttribute("similarityMsg", "아이디와 비밀번호의 유사도가 높습니다.");
//				redirectAttributes.addFlashAttribute("similarityMsg", "아이디와 비밀번호의 유사도가 높습니다.");
				return "/user/register";
			}
		}

		int result = service.signUp(user);

		if (result == 1) {
			return "redirect:/";
		} else {
			model.addAttribute("message", "[에러] 회원 가입 실패");
			model.addAttribute("messageDetail", "회원 가입에 실패했습니다. 정보를 다시 확인해주세요.");
			return "/user/register";
		}
	}
	
	// 아이디 비밀번호 유사성 체크
	private boolean checkSimilarity(String idPiece, String pw) {
		int idPieceSize = 3;
		int pwSize = pw.length();

		int idPieceHash = 0;
		int pwPieceHash = 0;
		int power = 1; // 제곱수

		for (int i = 0; i <= pwSize - idPieceSize; i++) {
			if (i == 0) {
				for (int j = 0; j < idPieceSize; j++) {
					// 234
					idPieceHash += idPiece.charAt(idPieceSize - 1 - j) * power;
					pwPieceHash += pw.charAt(idPieceSize - 1 - j) * power;
					if (j < idPieceSize - 1) {
						power *= 3;
					}
				}
			}else {
				pwPieceHash = 3 *(pwPieceHash - pw.charAt(i-1) * power) + pw.charAt(idPieceSize-1+i);
			}

			// 아이디 조각 해쉬값과 비밀번호 해쉬값이 같다면
			if(idPieceHash == pwPieceHash) {
				boolean isFind = true;
				// 우연히 해시값이 겹친 경우일 수 있으니 문자열 일치 여부 한번 더 검사
				for (int j = 0; j < idPieceSize; j++) {
					if(pw.charAt(i+j) != idPiece.charAt(j)) {
						isFind = false;
						break;
					}
				}
				
				//문자열이 같다면
				if(isFind){
					return true;
				}
			}
		}
		return false;
	}

	/** 회원가입 페이지 이동 */
	@GetMapping("/signUpView")
	public String signUpView() {

		return "/user/register";
	}

	/** 비밀번호 찾기 페이지 이동 */
	@GetMapping("/pwSearchView")
	public String pwSearchView() {

		return "/user/pwSearch";
	}

	/**
	 * 비밀번호 찾기(조회)
	 * 
	 * @throws Exception
	 */
	@PostMapping("/pwSearch")
	public String pwSearch(String id, String name, String phone, Model model, RedirectAttributes redirectAttributes)
			throws Exception {

		String password = service.getMyPw(id, name, phone);

		if (password != null) {
//			model.addAttribute("password", password);

			redirectAttributes.addFlashAttribute("password", password);
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("pwNotFound", "회원 비밀번호 조회에 실패했습니다.");
			return "redirect:/";
		}

	}

	/**
	 * 내 정보 조회
	 * 
	 * @throws Exception
	 */
	@GetMapping("/showInfo")
	public ModelAndView showInfo(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User loginInfo = (User) session.getAttribute("loginInfo");

		User dto = service.showInfo(loginInfo.getId());

		if (dto != null) {
			mav.addObject("dto", dto);
			mav.setViewName("/user/myInfo");
		} else {
			mav.addObject("message", "[에러] 내 정보 조회 실패");
			mav.addObject("messageDetail", "올바르지 않은 요청입니다.");
			mav.setViewName("/");
		}

		return mav;
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @throws Exception
	 */
	@PostMapping("/updateInfo")
	public String updateInfo(User user, Model model) throws Exception {
		int result = service.updateInfo(user);

		if (result == 1) {
			model.addAttribute("dto", user);

			return "/user/myInfo";
		} else {
			model.addAttribute("message", "[에러] 회원 정보 수정 실패");
			model.addAttribute("messageDetail", "정보 수정에 실패했습니다. 입력한 내용을 다시 확인해주세요.");
			return "/user/myInfo";
		}
	}

	/**
	 * 회원 정보 수정 페이지 이동
	 * 
	 * @throws Exception
	 */
	@GetMapping("/updateInfoView")
	public ModelAndView updateInfoView(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User loginInfo = (User) session.getAttribute("loginInfo");
		User dto = service.showInfo(loginInfo.getId());

		mav.setViewName("/");

		if (dto != null) {
			mav.addObject("dto", dto);
			mav.setViewName("/user/updateInfo");
		} else {
			mav.addObject("message", "[에러] 알 수 없는 에러");
			mav.addObject("messageDetail", "올바르지 않은 요청입니다.");
			mav.setViewName("/");
		}
		return mav;
	}

	/**
	 * 회원 탈퇴
	 * 
	 * @throws Exception
	 */
	@DeleteMapping("/deleteInfo")
	public ModelAndView deleteInfo(HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView mav = new ModelAndView();

		User loginInfo = (User) session.getAttribute("loginInfo");

		User dto = service.showInfo(loginInfo.getId());

		int result = service.deleteInfo(dto.getId());

		if (result == 1) {
			if (session.getAttribute("loginInfo") != null) {
				session.removeAttribute("loginInfo");
			}

			session.invalidate();

			mav.addObject("message", "회원 탈퇴가 완료되었습니다.");
			mav.addObject("messageDetail", "그 동안 이용해주셔서 감사합니다. 언제든지 다시 찾아와 주세요!");
			mav.setViewName("/result");
		} else {
			mav.addObject("message", "[에러] 알 수 없는 에러");
			mav.addObject("messageDetail", "올바르지 않은 요청입니다.");
			mav.setViewName("/error");
		}

		return mav;
	}

}
