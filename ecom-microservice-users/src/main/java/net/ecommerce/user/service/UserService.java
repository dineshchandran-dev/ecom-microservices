package net.ecommerce.user.service;


import net.ecommerce.user.dto.AddressDto;
import net.ecommerce.user.dto.RequestDto;
import net.ecommerce.user.dto.UserResponseDto;
import net.ecommerce.user.entity.Address;
import net.ecommerce.user.entity.User;
import net.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDto> viewAllUser() {
        return userRepository.findAll().stream().map(this::mapTOUserDto).collect(Collectors.toList());
    }

    public List<UserResponseDto> addUser(User user) {
        userRepository.save(user);
        return userRepository.findAll().stream().map(this::mapTOUserDto).collect(Collectors.toList());
    }

    public Optional<UserResponseDto> retrieveUser(Long id) {

        return userRepository.findById(id).map(this::mapTOUserDto);
    }

//    public Optional<UserResponseDto> updateUserDetails(User user) {
//        return userRepository.findById(user.getId())
//                .map(existingUser -> {
//
//                    if (user.getFirstName() != null &&
//                            !user.getFirstName().equalsIgnoreCase(existingUser.getFirstName())) {
//                        existingUser.setFirstName(user.getFirstName());
//                    }
//
//                    if (user.getEmail() != null &&
//                            !user.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
//                        existingUser.setEmail(user.getEmail());
//                    }
//
//                    if (user.getLastName() != null &&
//                            !user.getLastName().equalsIgnoreCase(existingUser.getLastName())) {
//                        existingUser.setLastName(user.getLastName());
//                    }
//
//                    if (user.getPhoneNumber() != null &&
//                            !user.getPhoneNumber().equals(existingUser.getPhoneNumber())) {
//                        existingUser.setPhoneNumber(user.getPhoneNumber());
//                    }
//
//                    if (user.getRole() != null &&
//                            user.getRole() != existingUser.getRole()) {
//                        existingUser.setRole(user.getRole());
//                    }
//                    if (user.getAddress() != null) {
//                        Address existingAddress = existingUser.getAddress();
//                        Address newAddress = user.getAddress();
//
//                        if (existingAddress == null) {
//                            existingUser.setAddress(newAddress);
//                        } else {
//                            if (newAddress.getStreet() != null &&
//                                    !newAddress.getStreet().equalsIgnoreCase(existingAddress.getStreet())) {
//                                existingAddress.setStreet(newAddress.getStreet());
//                            }
//                            if (newAddress.getCity() != null &&
//                                    !newAddress.getCity().equalsIgnoreCase(existingAddress.getCity())) {
//                                existingAddress.setCity(newAddress.getCity());
//                            }
//                            if (newAddress.getState() != null &&
//                                    !newAddress.getState().equalsIgnoreCase(existingAddress.getState())) {
//                                existingAddress.setState(newAddress.getState());
//                            }
//                            if (newAddress.getCountry() != null &&
//                                    !newAddress.getCountry().equalsIgnoreCase(existingAddress.getCountry())) {
//                                existingAddress.setCountry(newAddress.getCountry());
//                            }
//                            if (newAddress.getZipcode() != null &&
//                                    !newAddress.getZipcode().equalsIgnoreCase(existingAddress.getZipcode())) {
//                                existingAddress.setZipcode(newAddress.getZipcode());
//                            }
//
//                            existingUser.setAddress(existingAddress);
//                        }
//                    }
//
//                    userRepository.save(existingUser);
//
//                    return mapTOUserDto(existingUser);
//                });
//    }

    public UserResponseDto updateUserDetails(Long id, RequestDto requestDto){
        return  userRepository.findById(id).map(existing->{
            mapToUpdateUser(existing,requestDto);
            User saved=userRepository.save(existing);
            return mapTOUserDto(saved);
        }).orElseThrow(()->new RuntimeException("user Not Found :"+ id));
    }

    private UserResponseDto mapTOUserDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(String.valueOf(user.getId()));
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhoneNumber());

        if (user.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZipcode(user.getAddress().getZipcode());
            userResponseDto.setAddress(addressDto);
        }
        return userResponseDto;


    }

    public void mapToUpdateUser(User user, RequestDto requestDto) {

        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhone());

        if (requestDto.getAddress() != null) {
            AddressDto addressDto = requestDto.getAddress();
            Address address = user.getAddress();

            if (address == null) {
                address = new Address();
            }

            address.setStreet(addressDto.getStreet());
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setCountry(addressDto.getCountry());
            address.setZipcode(addressDto.getZipcode());

            user.setAddress(address);
        }

        mapTOUserDto(user);
    }





}
