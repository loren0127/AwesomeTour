package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

public interface AccomMapper {
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//���� �̹��� �ҷ�����
	public ImageCommand selectImage(Integer im_ac_num);
	
	//==========���� ������==========//
	//����ȣ��Ʈ ���� �ҷ�����
	public List<AccomCommand> selectRecommendList(Map<String,Object> map);
	
	//���� �ҷ�����
	@Select("SELECT * FROM (SELECT re.*, rownum rnum FROM (SELECT re_acc_num, re_email, re_reg_date, re_content FROM accom_review ORDER BY re_reg_date DESC)re) WHERE rnum >= 1 AND rnum <= 4")
	public List<ReviewCommand> selectReviewList();
	
	//�α� ���� �ҷ�����
	@Select("")
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
}
