package com.yeoblee.domain;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class QnaListeners {
	
	@PrePersist
    public void prePersist(Qna qna) {
        System.out.println("pre persist: {}" + qna);
    }
    
    @PostPersist
    public void postPersist(Qna qna) {
        System.out.println("post persist: {}" + qna);
    }
    
    @PreUpdate
    public void preUpdate(Qna qna) {
        System.out.println("pre update: {}" + qna);
    }
    
    @PostUpdate
    public void postUpdate(Qna qna) {
        System.out.println("post update: {}" +  qna);
    }
    
    @PreRemove
    public void preRemove(Qna qna) {
        System.out.println("pre remove: {}" +  qna);
    }
    
    @PostRemove
    public void postRemove(Qna qna) {
        System.out.println("post remove: {}" +  qna);
    }

}