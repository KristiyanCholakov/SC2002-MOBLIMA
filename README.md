# SC2002 - MOBLIMA

Welcome to the OOP Movie Booking System! This project is an object-oriented cinema booking application developed as part of our SC2002 Object-Oriented Design and Programming group assignment (AY22/23 Semester 1). The application demonstrates robust design practices by leveraging SOLID principles and key OOP concepts such as encapsulation and abstraction.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Design and Architecture](#design-and-architecture)
- [Testing](#testing)
- [Future Enhancements](#future-enhancements)
- [Usage](#usage)
- [Acknowledgements](#acknowledgements)

## Project Overview
The OOP Movie Booking System provides a complete solution for booking cinema tickets with a focus on clean, maintainable code. Users can register, log in, and book movie tickets while the system enforces age restrictions and applies dynamic pricing based on movie formats and time factors. Administrators have additional privileges to manage movies, schedules, and pricing.

## Features
- **User Authentication**: Secure registration and login for both users and administrators.
- **Dynamic Booking System**:
  - **Age Restrictions**: Enforces rules based on movie ratings (PG, NC16, M18).
  - **Variable Pricing**: Calculates ticket prices dynamically based on factors such as movie format (2D, 3D, 4DX, IMAX), user profile (adult, student, senior), and time (weekday/weekend).
- **Administrative Controls**:
  - Manage movies (add, edit, delete).
  - Configure movie schedules.
  - Update ticket prices and special pricing parameters.
- **Robust Error Handling**: Abstracts complex error management away from the user interface.
- **Extensible Design**: Built with SOLID principles in mind to easily incorporate new features.

## Design and Architecture
Our solution is underpinned by modern object-oriented design principles:

- **Single Responsibility Principle (SRP)**: Each class and function is designed to have a single purpose, which simplifies maintenance and minimizes side effects.
- **Open-Closed Principle (OCP)**: The system is structured to allow easy extensions without modifying existing code—for example, extending the pricing class to handle different ticket types.
- **Interface Segregation Principle (ISP)**: Interfaces expose only the necessary methods, ensuring clients are not burdened with unused functionalities.
- **Encapsulation**: Critical data is kept private within classes and is accessed via well-defined getters and setters.
- **Abstraction**: Implementation details (such as error handling during account creation) are hidden from the end user, resulting in a cleaner and more user-friendly interface.

A comprehensive UML Class Diagram is included in the project documentation to further illustrate the system’s architecture.

## Testing
The project underwent rigorous testing to validate its functionality:
- **Registration & Login**: Tested with both valid and invalid credentials to ensure secure access.
- **Age Restrictions**: Confirmed correct behavior for different movie ratings by verifying user age requirements.
- **Ticket Pricing**: Verified that dynamic pricing calculations work for various conditions (e.g., 2D, 3D, 4DX, IMAX formats; weekday vs. weekend; senior and student discounts).
- **Admin Functions**: Ensured that movie and schedule management operations perform as expected.

All tests met the expected outcomes, demonstrating the system’s robustness.

## Future Enhancements
Planned improvements include:
- **Movie Recommendation System**: Personalized suggestions based on user booking history and favorite genres.
- **Combo Meal Pricing**: Integration of food and beverage deals alongside ticket bookings, with special pricing for combo offers.

## Usage
1. **User Registration & Login**: Create an account or log in using your existing credentials.
2. **Movie Booking**: Browse available movies, select a showtime, and proceed with booking.
3. **Admin Management**: If you have admin rights, manage movie details, schedules, and pricing through the dedicated admin interface.

## Acknowledgements
This project was developed as part of the SC2002 Object-Oriented Design and Programming course. Special thanks to the team members:
- Cholakov Kristiyan Kamenov (U2123543B)
- Brendon Tan (U2121250H)
- Denzyl David Peh (U2122190F)
- Goh Kuan Wei, Eldrick (U2121107B)
- Zhang Xiaoyang (U2121950J)

## Demo
[▶️ Watch the demo video on YouTube](https://youtu.be/FUokMTk_E2I)
