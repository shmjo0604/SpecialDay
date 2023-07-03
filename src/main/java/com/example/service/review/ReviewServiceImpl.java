package com.example.service.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Apply;
import com.example.entity.Review;
import com.example.entity.ReviewImage;
import com.example.entity.ReviewImageProjection;
import com.example.entity.ReviewView;
import com.example.repository.ApplyRepository;
import com.example.repository.ReviewImageRepository;
import com.example.repository.ReviewViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final ReviewViewRepository r1Repository;
    final ApplyRepository applyRepository;
    final ReviewImageRepository reviewImageRepository;

    @Override
    public int insertReview(Review obj) {

        try {
            long no = obj.getNo();

            Apply ret = applyRepository.findById(no).orElse(null);

            obj.setApply(ret);

            r1Repository.save(obj);

            ret.setChk(4);

            applyRepository.save(ret);

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int countReview(String id) {
        try {
            return (int) r1Repository.countByIdContaining(id);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ReviewView> selectlistReviewview(String id) {
        try {
            return r1Repository.findById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReviewView> selectReviewByIdPagenation(String id, int start, int end) {
        try {
            return r1Repository.selectReviewByIdPagenation(id, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertReviewImage(List<ReviewImage> obj) {

        try {
            reviewImageRepository.saveAll(obj);
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }

    @Override
    public List<ReviewView> selectByClasscode(long classcode, int first, int last) {
        try {
            List<ReviewView> list = r1Repository.selectByClasscode(classcode, first, last);

            return list;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long countByClasscode(long classcode) {
        try {
            return r1Repository.countByClasscode(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ReviewImageProjection> selectReviewImageNoList(long reviewno) {
        try {
            return reviewImageRepository.findByReview_No(reviewno);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReviewImage selectReivewImageOne(long no) {
        try {
            return reviewImageRepository.findById(no).orElse(null);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 리뷰 하나만 조회
    @Override
    public ReviewView selectReviewOne(String id, long no) {
        try {
            return r1Repository.findByIdAndNo(id, no);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int avgReview(String id) {
        try {
            return r1Repository.avgReview(id);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<ReviewView> selectReviewByIdPagenationAsc(String id, int start, int end) {
        try {
            return r1Repository.selectReviewByIdPagenationAsc(id, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReviewImage selectReviewImage(long no) {
        try {
            return reviewImageRepository.findByNo(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Long> reviewImagelistNo(long no) {
        try {
            return reviewImageRepository.reviewImagelistNo(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateReviewHit(long no) {
       try {
        return r1Repository.updateReviewHit(no);
       } catch (Exception e) {
        e.printStackTrace();
        return -1;
       }
    }

}


