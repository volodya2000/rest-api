package com.udemy.restudemy.utils;

import com.udemy.restudemy.dto.AddressDto;
import com.udemy.restudemy.model.Address;
import com.udemy.restudemy.model.User;
import com.udemy.restudemy.repository.UserRepository;
import com.udemy.restudemy.response.AddressRest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Component
@Data
@NoArgsConstructor
public class AddressMapper {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public AddressMapper(ModelMapper modelMapper,UserRepository userRepository)
    {
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }
    public Address toEntity(AddressDto addressDto)
    {
        return Objects.isNull(addressDto)?null:modelMapper.map(addressDto,Address.class);
    }

    public AddressDto toDto(Address address)
    {
        return Objects.isNull(address)?null:modelMapper.map(address,AddressDto.class);
    }

    public AddressRest toAddressRest(AddressDto address)
    {
        return Objects.isNull(address)?null:modelMapper.map(address,AddressRest.class);
    }
    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Address.class, AddressDto.class)
                .addMappings(m -> m.skip(AddressDto::setUser_id)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(AddressDto.class, AddressRest.class)
                .addMappings(m -> m.skip(AddressRest::setUser_id)).setPostConverter(toRestConverter());

    }



    private Converter<Address, AddressDto> toDtoConverter() {
        return context -> {
            Address source = context.getSource();
            AddressDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Address source, AddressDto destination) {
        destination.setUser_id((Objects.isNull(source)|| Objects.isNull(source.getId())) ? null : source.getUser().getId());
    }


    private Converter<AddressDto, AddressRest> toRestConverter() {
        return context -> {
            AddressDto source = context.getSource();
            AddressRest destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(AddressDto source, AddressRest destination) {
        Optional<User> user=userRepository.findById(source.getUser_id());
        user.ifPresent(value->{destination.setUser_id(value.getUser_id());});
    }
}
