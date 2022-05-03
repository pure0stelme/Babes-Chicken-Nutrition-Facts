package com.usthe.manager.service.impl;

import com.usthe.common.entity.manager.Tag;
import com.usthe.manager.dao.TagDao;
import com.usthe.manager.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public void addTags(List<Tag> tags) {
        tagDao.saveAll(tags);
    }

    @Override
    public void modifyTag(Tag tag) {
        Optional<Tag> tagOptional = tagDao.findById(tag.getId());
        if (tagOptional.isPresent()) {
            tagDao.save(tag);
        } else {
            throw new IllegalArgumentException("The tag is not existed");
        }
    }

    @Override
    public Page<Tag> getTags(Specification<Tag> specification, PageRequest pageRequest) {
        return tagDao.findAll(specification, pageRequest);
    }

    @Override
    public void deleteTags(HashSet<Long> ids) {
        tagDao.deleteTagsByIdIn(ids);
    }
}
