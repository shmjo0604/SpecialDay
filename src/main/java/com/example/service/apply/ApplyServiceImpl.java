package com.example.service.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Apply;
import com.example.dto.ApplyStatus;
import com.example.dto.ApplyStatusView;
import com.example.dto.ApplyView;
import com.example.entity.Member;
import com.example.entity.Notification;
import com.example.mapper.ApplyMapper;
import com.example.mapper.ClassUnitMapper;
import com.example.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplyServiceImpl implements ApplyService {

    @Autowired ApplyMapper aMapper;
    @Autowired ClassUnitMapper unitMapper;
    @Autowired NotificationRepository nRepository;

    final String format = "ApplyServiceImpl => {}";

    @Override
    public int insertApplyBatch(List<Apply> list) {
        try {
            int ret = aMapper.insertApplyBatch(list);

            if (ret == list.size()) {

                int ret2 = aMapper.updateClassUnitApplySuccess(list);

                if( ret2 == list.size()) {
                    Apply apply1 = list.get(0);

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", apply1.getMemberid());
                    map.put("count", list.size());
    
                    List<Long> list1 = aMapper.selectInsertedApplyNoList(map);
    
                    log.info(format, list1.toString());
    
                    List<ApplyStatus> list2 = new ArrayList<>();
    
                    for (long a : list1) {
    
                        ApplyStatus applyStatus = new ApplyStatus();
                        applyStatus.setApplyno(a);
                        applyStatus.setChk(1);
    
                        list2.add(applyStatus);
    
                    }
    
                    int result = aMapper.insertApplyStatusBatch(list2);
    
                    if(result == list2.size()) {
    
                        List<Notification> notifications = new ArrayList<>();

                        for(Apply apply2 : list) {

                            Notification notification = new Notification();
                            
                            Member member = new Member();
                            member.setId(apply2.getOwnerid());

                            notification.setMember(member);
                            notification.setUrl("/classunit/myunit.do?menu=2&classcode=" + apply2.getClasscode());
                            notification.setContent(apply2.getMemberid() + "님이 클래스 신청하였습니다.");
                            notification.setType("신청");

                            notifications.add(notification);

                        }

                        nRepository.saveAll(notifications);
                        
                        return 1;
                    }
                }
                
                return 0;
            }

            else {

                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int insertApplyStatusBatch(List<ApplyStatus> list) {
        try {
            return aMapper.insertApplyStatusBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateApplyCancel(Apply obj) {
        try {

            obj.setOwnerid(unitMapper.selectClassUnitViewOne(obj.getUnitno()).getMemberid());
            
            // 1. 신청 취소 (Apply table chk update 1 -> 2)

            int ret1 = aMapper.updateApplyCancel(obj);

            if(ret1 == 1) {

                // 2. 신청 취소 상태 기록 (ApplyStatus Table에 insert)

                int ret2 = aMapper.insertApplyStatusOne(obj.getNo(), 2);

                if(ret2 == 1) {

                    // 3. 신청 취소한 클래스 유닛 인원수 변경(취소 인원만큼 cnt -)

                    int ret3 = aMapper.updateClassUnitApplyCancel(obj);

                    if(ret3 == 1) {

                        // 4. 신청 취소 알림 전송(ownerid 필요)

                        Notification notification = new Notification();

                        Member member = new Member();

                        member.setId(obj.getOwnerid());

                        notification.setMember(member);
                        notification.setType("신청");
                        notification.setContent(obj.getOwnerid() + "님이 클래스 신청 취소하였습니다.");
                        notification.setUrl("/classunit/applymanage.do?classcode="+obj.getClasscode()+"&unitno="+obj.getUnitno());

                        nRepository.save(notification);

                        return 1;

                    }

                }

            }

            return 0;

            
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateApplyComplete(List<Apply> list) {
        try {
            return aMapper.updateApplyComplete(list);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateClassUnitApplySuccess(List<Apply> list) {
        try {
            return aMapper.updateClassUnitApplySuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateClassUnitApplyCancel(Apply obj) {
        try {
            return aMapper.updateClassUnitApplyCancel(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ApplyView> selectApplyListById(Map<String, Object> map) {
        try {
            return aMapper.selectApplyListById(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long countApplyList(String id) {
        try {
            return aMapper.countApplyList(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long countApplyListOne(String id) {
        try {
            return aMapper.countApplyListOne(id);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public long countApplyListTwo(String id) {
        try {
            return aMapper.countApplyListTwo(id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long countApplyListThree(String id) {
        try {
            return aMapper.countApplyListThree(id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ApplyStatusView> selectApplyStatusListById(Map<String, Object> map) {
        try {
            return aMapper.selectApplyStatusListById(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ApplyView> selectApplyListByIdOne(Map<String, Object> map) {
        try {
            return aMapper.selectApplyListByIdOne(map);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ApplyView> selectApplyListByIdTwo(Map<String, Object> map) {
        try {
            return aMapper.selectApplyListByIdTwo(map);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ApplyView> selectApplyListByIdThree(Map<String, Object> map) {
        try {
            return aMapper.selectApplyListByIdThree(map);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ApplyView> selectApplyViewListByUnitno(long unitno) {
        try {
            return aMapper.selectApplyViewListByUnitno(unitno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int updateApplyChk(long no) {
        try {
            return aMapper.updateApplyChk(no);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int insertApplyStatusOne(long applyno, int chk) {
        try {
            return aMapper.insertApplyStatusOne(applyno, chk);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
