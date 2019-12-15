package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelGroups.ItemGroup;
import com.example.vksbermvvm.domain.model.IProfileConverter;
import com.example.vksbermvvm.domain.model.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует список групп из data сущностей в domain
 *
 * @author Цунский Роман on 2019-12-15
 */
public class GroupListConverter implements IProfileConverter<Groups, List<Group>> {

    private final List<Group> groupList = new ArrayList<>();

    @NonNull
    @Override
    public List<Group> convert(@NonNull Groups groups) {
        groupList.clear();

        for (ItemGroup responseGroups : groups.response.items) {

            groupList.add(new Group(responseGroups.id,
                    responseGroups.name,
                    responseGroups.screenName,
                    responseGroups.isClosed,
                    responseGroups.photo200));
        }
        return groupList;
    }
}
