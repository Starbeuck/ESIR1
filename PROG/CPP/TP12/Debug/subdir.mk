################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../auto.cc \
../bus.cc \
../client.cc \
../ferry.cc \
../vehicule.cc 

CC_DEPS += \
./auto.d \
./bus.d \
./client.d \
./ferry.d \
./vehicule.d 

OBJS += \
./auto.o \
./bus.o \
./client.o \
./ferry.o \
./vehicule.o 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


