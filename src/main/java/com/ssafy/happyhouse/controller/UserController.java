package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.dto.LoginUser;
import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.User2;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;

	/**
	 * 로그인
	 * 
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUser loginUser, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {

			User user = service.login(loginUser.getId());
			// parameter로 받은 유저의 비밀번호와 DB에 저장된 암호화값 비교 (salt값을 따로 저장하지 않으므로 Bcrypt의 메서드를
			// 이용하여 parameter로 받은 비밀번호와 hash값을 비교)
			if (user != null && BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
				String token = jwtService.create("userid", user.getId(), "access-token");
//				session.setAttribute("loginInfo", user);
				resultMap.put("access-token", token);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

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
	public ResponseEntity<?> signUp(@RequestBody User user) throws Exception {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		HttpStatus status = null;

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

			if (checkSimilarity(it.next(), password)) {
				// 유사도가 높으면

				hashMap.put("message", "similarityMsg");
				status = HttpStatus.CONFLICT;

//				model.addAttribute("similarityMsg", "아이디와 비밀번호의 유사도가 높습니다.");
//				redirectAttributes.addFlashAttribute("similarityMsg", "아이디와 비밀번호의 유사도가 높습니다.");
//				return "/user/register";
			}
		}

		String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashPassword);

		int result = service.signUp(user);

		if (result == 1) {
			hashMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} else {
			hashMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(hashMap, status);
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
			} else {
				pwPieceHash = 3 * (pwPieceHash - pw.charAt(i - 1) * power) + pw.charAt(idPieceSize - 1 + i);
			}

			// 아이디 조각 해쉬값과 비밀번호 해쉬값이 같다면
			if (idPieceHash == pwPieceHash) {
				boolean isFind = true;
				// 우연히 해시값이 겹친 경우일 수 있으니 문자열 일치 여부 한번 더 검사
				for (int j = 0; j < idPieceSize; j++) {
					if (pw.charAt(i + j) != idPiece.charAt(j)) {
						isFind = false;
						break;
					}
				}

				// 문자열이 같다면
				if (isFind) {
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
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> showInfo(@PathVariable String userid, HttpServletRequest request) {
//		User loginInfo = (User) session.getAttribute("loginInfo");
		Map<String, Object> resultMap = new HashMap();
		HttpStatus status = null;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				User loginInfo = service.showInfo(userid);
				UserInfo userInfo = new UserInfo(loginInfo.getId(), loginInfo.getName(), loginInfo.getAddress(),
						loginInfo.getPhone());
				resultMap.put("userInfo", userInfo);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	/**
	 * 아이디 조회
	 * 
	 * @throws Exception
	 */
	@GetMapping("/id/{userid}")
	public ResponseEntity<Map<String, Object>> findByKakaoId(@PathVariable String userid) {
		Map<String, Object> resultMap = new HashMap();
		HttpStatus status = null;

		try {
			String userId = service.findById(userid);
			resultMap.put("userId", userId);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	/**
	 * 회원 정보 수정
	 * 
	 * @throws Exception
	 */
	@PutMapping("/updateInfo")
	public ResponseEntity<Map<String, Object>> updateInfo(@RequestBody UserInfo userInfo, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap();
		HttpStatus status = null; 

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				System.out.println(userInfo.toString());
				int result = service.updateInfo(userInfo);
				if (result == 1) {
					resultMap.put("message", "success");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "fail");
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

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
	@DeleteMapping("/deleteInfo/{userid}")
	public ResponseEntity<?> deleteInfo(@PathVariable String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			int result = service.deleteInfo(userid);
			if (result == 1) {
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//		ModelAndView mav = new ModelAndView();
//
//		User loginInfo = (User) session.getAttribute("loginInfo");
//
//		User dto = service.showInfo(loginInfo.getId());
//
//		int result = service.deleteInfo(dto.getId());
//
//		if (result == 1) {
//			if (session.getAttribute("loginInfo") != null) {
//				session.removeAttribute("loginInfo");
//			}
//
//			session.invalidate();
//
//			mav.addObject("message", "회원 탈퇴가 완료되었습니다.");
//			mav.addObject("messageDetail", "그 동안 이용해주셔서 감사합니다. 언제든지 다시 찾아와 주세요!");
//			mav.setViewName("/result");
//		} else {
//			mav.addObject("message", "[에러] 알 수 없는 에러");
//			mav.addObject("messageDetail", "올바르지 않은 요청입니다.");
//			mav.setViewName("/error");
//		}
//
//		return mav;
	}

}
