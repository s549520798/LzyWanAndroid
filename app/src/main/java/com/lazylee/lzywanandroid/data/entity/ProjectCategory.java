package com.lazylee.lzywanandroid.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectCategory implements Parcelable {

    private int id;
    private int courseId;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;

    protected ProjectCategory(Parcel in) {
        id = in.readInt();
        courseId = in.readInt();
        name = in.readString();
        order = in.readInt();
        parentChapterId = in.readInt();
        visible = in.readInt();
    }

    public static final Creator<ProjectCategory> CREATOR = new Creator<ProjectCategory>() {
        @Override
        public ProjectCategory createFromParcel(Parcel in) {
            return new ProjectCategory(in);
        }

        @Override
        public ProjectCategory[] newArray(int size) {
            return new ProjectCategory[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(courseId);
        parcel.writeString(name);
        parcel.writeInt(order);
        parcel.writeInt(parentChapterId);
        parcel.writeInt(visible);
    }

    @Override
    public String toString() {
        return "ProjectCategory{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", visible=" + visible +
                '}';
    }
}
