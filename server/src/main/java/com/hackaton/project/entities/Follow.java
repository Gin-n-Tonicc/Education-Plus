package com.hackaton.project.entities;

import jakarta.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "followed_business_id")
    private Business gotFollowedBusiness;
    @ManyToOne
    @JoinColumn(name = "followed_student_id")
    private Student followedStudent;

    public Student getFollowedStudent() {
        return followedStudent;
    }

    public void setFollowedStudent(Student followedStudent) {
        this.followedStudent = followedStudent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getGotFollowedBusiness() {
        return gotFollowedBusiness;
    }

    public void setGotFollowedBusiness(Business gotFollowedBusiness) {
        this.gotFollowedBusiness = gotFollowedBusiness;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", gotFollowedBusiness=" + gotFollowedBusiness +
                ", followedStudent=" + followedStudent +
                '}';
    }
}
