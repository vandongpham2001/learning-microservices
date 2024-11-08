package com.devteria.profile.service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
