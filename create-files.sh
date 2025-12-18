#!/bin/bash

BASE=src/main/java/com/example/demo

# --- create directories (safe even if they already exist) ---
mkdir -p $BASE/controller
mkdir -p $BASE/dto
mkdir -p $BASE/exception
mkdir -p $BASE/model
mkdir -p $BASE/repository
mkdir -p $BASE/service
mkdir -p $BASE/security
mkdir -p $BASE/config

# --- controllers ---
touch $BASE/controller/AuthController.java
touch $BASE/controller/GuestController.java
touch $BASE/controller/RoomBookingController.java
touch $BASE/controller/DigitalKeyController.java
touch $BASE/controller/KeyShareRequestController.java
touch $BASE/controller/AccessLogController.java

# --- dtos ---
touch $BASE/dto/LoginRequest.java
touch $BASE/dto/RegisterRequest.java
touch $BASE/dto/AuthResponse.java
touch $BASE/dto/GuestDTO.java
touch $BASE/dto/RoomBookingDTO.java
touch $BASE/dto/DigitalKeyDTO.java
touch $BASE/dto/KeyShareRequestDTO.java

# --- exceptions ---
touch $BASE/exception/ResourceNotFoundException.java
touch $BASE/exception/DuplicateResourceException.java
touch $BASE/exception/GlobalExceptionHandler.java

# --- models ---
touch $BASE/model/Guest.java
touch $BASE/model/RoomBooking.java
touch $BASE/model/DigitalKey.java
touch $BASE/model/KeyShareRequest.java
touch $BASE/model/AccessLog.java

# --- repositories ---
touch $BASE/repository/GuestRepository.java
touch $BASE/repository/RoomBookingRepository.java
touch $BASE/repository/DigitalKeyRepository.java
touch $BASE/repository/KeyShareRequestRepository.java
touch $BASE/repository/AccessLogRepository.java

# --- services ---
touch $BASE/service/GuestService.java
touch $BASE/service/RoomBookingService.java
touch $BASE/service/DigitalKeyService.java
touch $BASE/service/KeyShareRequestService.java
touch $BASE/service/AccessLogService.java

# --- security ---
touch $BASE/security/JwtTokenProvider.java
touch $BASE/security/JwtAuthenticationFilter.java
touch $BASE/security/CustomUserDetailsService.java
touch $BASE/security/GuestPrincipal.java

# --- config ---
touch $BASE/config/SecurityConfig.java
touch $BASE/config/SwaggerConfig.java

echo "✅ All directories and files created successfully"
