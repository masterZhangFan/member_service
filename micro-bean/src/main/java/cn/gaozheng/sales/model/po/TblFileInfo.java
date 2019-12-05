package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tbl_file_info")
public class TblFileInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "`file_id`")
    private Integer fileId;

    /**
     * 原始文件名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 扩展名
     */
    @Column(name = "`extend_name`")
    private String extendName;

    /**
     * 文件大小
     */
    @Column(name = "`size`")
    private Long size;

    /**
     * 文件路径
     */
    @Column(name = "`path`")
    private String path;

    /**
     * 文件被引用次数，为0时会被删除
     */
    @Column(name = "`refrence_count`")
    private Integer refrenceCount;

    /**
     * 上传时间
     */
    @Column(name = "`upload_time`")
    private Date uploadTime;

    /**
     * @return file_id
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取原始文件名
     *
     * @return name - 原始文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置原始文件名
     *
     * @param name 原始文件名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取扩展名
     *
     * @return extend_name - 扩展名
     */
    public String getExtendName() {
        return extendName;
    }

    /**
     * 设置扩展名
     *
     * @param extendName 扩展名
     */
    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    /**
     * 获取文件大小
     *
     * @return size - 文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 设置文件大小
     *
     * @param size 文件大小
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 获取文件路径
     *
     * @return path - 文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件路径
     *
     * @param path 文件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取文件被引用次数，为0时会被删除
     *
     * @return refrence_count - 文件被引用次数，为0时会被删除
     */
    public Integer getRefrenceCount() {
        return refrenceCount;
    }

    /**
     * 设置文件被引用次数，为0时会被删除
     *
     * @param refrenceCount 文件被引用次数，为0时会被删除
     */
    public void setRefrenceCount(Integer refrenceCount) {
        this.refrenceCount = refrenceCount;
    }

    /**
     * 获取上传时间
     *
     * @return upload_time - 上传时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadTime 上传时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}