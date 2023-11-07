cmake_minimum_required(VERSION 3.10.2)
project(VK2TG)

set(CMAKE_CXX_STANDARD 20)
set(Boost_NO_WARN_NEW_VERSIONS 1)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall")
set(Boost_USE_MULTITHREADED ON)

find_package(Threads REQUIRED)
find_package(OpenSSL REQUIRED)
find_package(Boost COMPONENTS system filesystem REQUIRED)
find_package(CURL)

include_directories(D:/Dev/vcpkg/scripts/buildsystems/vcpkg.cmake ${OPENSSL_INCLUDE_DIR})
include_directories(${Boost_INCLUDE_DIR})

if (CURL_FOUND)
    include_directories(${CURL_INCLUDE_DIRS})
    add_definitions(-DHAVE_CURL)
endif()

add_executable(VK2TG main.cpp)

target_link_libraries(VK2TG D:/Dev/vcpkg/installed/x64-windows/lib/TgBot.lib ${CMAKE_THREAD_LIBS_INIT} ${OPENSSL_LIBRARIES} ${CURL_LIBRARIES})
target_link_libraries(VK2TG -static)
target_link_libraries(VK2TG ${Boost_LIBRARIES})