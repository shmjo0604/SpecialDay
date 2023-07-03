package com.example.service.classproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ClassImage;
import com.example.dto.ClassProduct;
import com.example.entity.ClassInquiry;
import com.example.entity.ClassInquiryView;
import com.example.entity.ClassInquiryViewVo;
import com.example.mapper.ClassManageMapper;
import com.example.repository.ClassImageRepository;
import com.example.repository.ClassInquiryViewRepository;

@Service
public class ClassManageServiceImpl implements ClassManageService {

    @Autowired
    ClassManageMapper cMapper;
    @Autowired
    ClassInquiryViewRepository cRepository;
    @Autowired
    ClassImageRepository classimageRepository;

    @Override
    public List<ClassProduct> selectMyClassList(String id) {
        try {
            List<ClassProduct> list = cMapper.selectMyClassList(id);
            if (list != null) {
                for (ClassProduct classProduct : list) {
                    long classcode = classProduct.getClasscode();
                    long mainImg = cMapper.selectClassMainImageNo(classcode);
                    long profileImg = cMapper.selectClassProfileImageNo(classcode);

                    classProduct.setMainImg(mainImg);
                    classProduct.setProfileImg(profileImg);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ClassProduct selectClassOne(long classcode) {
        try {
            return cMapper.selectClassOne(classcode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateClassInactive(ClassProduct obj) {
        try {
            return cMapper.updateClassInactive(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateClassOne(ClassProduct obj) {
        try {

            return cMapper.updateClassOne(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long selectClassProfileImageNo(long classcode) {
        try {
            return cMapper.selectClassProfileImageNo(classcode);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long selectClassMainImageNo(long classcode) {
        try {
            return cMapper.selectClassMainImageNo(classcode);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Long> selectClassSubImageNoList(long classcode) {
        try {
            return cMapper.selectClassSubImageNoList(classcode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ClassImage selectClassImageOne(long no) {
        try {
            return cMapper.selectClassImageOne(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateClassImageOne(List<com.example.entity.ClassImage> subImg, com.example.entity.ClassImage profileImg,
            com.example.entity.ClassImage mainImg, long classcode) {
        try {
            long mainImg1 = cMapper.selectClassMainImageNo(classcode);
            long profileImg1 = cMapper.selectClassProfileImageNo(classcode);
            List<Long> subImg1 = cMapper.selectClassSubImageNoList(classcode);

            com.example.entity.ClassImage classimage1 = classimageRepository.findById(mainImg1).orElse(null);
            com.example.entity.ClassImage classimage2 = classimageRepository.findById(profileImg1).orElse(null);
            // List<com.example.entity.ClassImage> classimage3 = classimageRepository.findAllById(subImg1);



            //System.out.println(classimage3);

            if (mainImg.getFilesize() > 0) {
                classimage1.setFiledata(mainImg.getFiledata());
                classimage1.setFilename(mainImg.getFilename());
                classimage1.setFilesize(mainImg.getFilesize());
                classimage1.setFiletype(mainImg.getFiletype());

                classimageRepository.save(classimage1);
            }

            if (profileImg.getFilesize() > 0) {
                classimage2.setFiledata(profileImg.getFiledata());
                classimage2.setFilename(profileImg.getFilename());
                classimage2.setFilesize(profileImg.getFilesize());
                classimage2.setFiletype(profileImg.getFiletype());

                classimageRepository.save(classimage2);
            }

            // if (subImg.isEmpty() == false) {
            //     for ( int i=0; i) {
            //         // ClassImage classSub = new ClassImage();
            //         com.example.entity.ClassImage classImage = classimageRepository.findById(image).orElse(null);
            //         classImage
            //         // for (com.example.entity.ClassImage image2 : subImg) {
            //         //     image.setFiledata(image2.getFiledata());
            //         //     image.setFilename(image2.getFilename());
            //         //     image.setFiletype(image2.getFiletype());
            //         //     image.setFilesize(image2.getFilesize());
            //         // }
            //     }
            //     classimageRepository.saveAll(classimage3);
            // }

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteClassImageOne(long no) {
        try {
            return cMapper.deleteClassImageOne(no);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ClassInquiryViewVo> selectClassInquiryList(String owner, int first, int last) {
        try {
            return cRepository.selectByOwnerOrderByNoDescPaging(owner, first, last);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateClassNonactive(ClassProduct obj) {
        try {
            return cMapper.updateClassNonactive(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ClassInquiryView selectClassInquiryOne(long no) {
        try {
            return cRepository.findByNo(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateClassActive(ClassProduct obj) {
        try {
            return cMapper.updateClassActive(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int insertClassInquiryAnswerOne(ClassInquiryView obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertClassInquiryAnswerOne'");
    }

    @Override
    public int updateClassAnswer(ClassInquiry obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClassAnswer'");
    }

    @Override
    public long selectClassInquiryListCount(String owner) {
        try {
            return cRepository.countByOwner(owner);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long countByOwnerAndChk(String owner, int chk) {
        try {
            return cRepository.countByOwnerAndChk(owner, chk);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ClassInquiryViewVo> selectByOwnerANDChkOrderByNoDescPaging(String owner, int first, int last, int chk) {
        try {
            return cRepository.selectByOwnerANDChkOrderByNoDescPaging(owner, first, last, chk);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
