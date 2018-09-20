package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

public interface AccomMapper {
	//��� ����
	public List<AccomCommand> selectSearchList(Map<String,Object> map);
	
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//���� �̹��� �ҷ�����
	public ImageCommand selectImage(Integer im_ac_num);
	
	//==========���� ������==========//
	//����ȣ��Ʈ ���� �ҷ�����
	public List<AccomCommand> selectRecommendList(Map<String,Object> map);
	
	//���� �ҷ�����
	@Select("SELECT * FROM (SELECT re.*, rownum rnum FROM (SELECT re_acc_num, re_email, re_reg_date reg_date, re_content FROM accom_review ORDER BY re_reg_date DESC)re) WHERE rnum >= 1 AND rnum <= 4")
	public List<ReviewCommand> selectReviewList();
	
	//�α� ���� �ҷ�����
	@Select("SELECT * FROM (SELECT gr.*, rownum rnum FROM (SELECT g_num, g_name, g_address2, g_hobby, g_reg_date, g_isprivate, g_issearch FROM group_table WHERE g_isprivate = 0 AND g_issearch = 0 ORDER BY g_reg_date DESC)gr) WHERE rnum >=1 AND rnum <= 4")
	public List<GroupCommand> selectGroupList();
	
	//���� �̹��� �ҷ����� --> �׷� �� ���
}
