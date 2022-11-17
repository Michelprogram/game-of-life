<div id="top"></div>



<!-- PROJECT LOGO -->
<br />
<div align="center">
    <img src="./images/gospers.gif" alt="Logo" width="80" height="80">

<h3 align="center">Game of Life</h3>

  <p align="center">Clone of the Conway's Game of Life</p>
</div>

 <br />  

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">🧭 About The Project</a>
      <ul>
        <li><a href="#built-with">🏗️ Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">📋 Getting Started</a>
      <ul>
        <li><a href="#prerequisites">🗺️ Prerequisites</a></li>
        <li><a href="#installation">⚙️ Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">💾 Usage</a></li>
    <li><a href="#contributing">🔗 Contributing</a></li>
    <li><a href="#license">📰 License</a></li>
    <li><a href="#contact">📫 Contact</a></li>
    <li><a href="#acknowledgments">⛱️ Acknowledgments</a></li>
  </ol>
</details>

<br>



<!-- ABOUT THE PROJECT -->
## 🧭 About The Project

### 🏗️ Built With

List of technologies / frameworks used during the project.

* [![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/fr/)
* Junit https://junit.org/junit5/

<p align="right"><a href="#top">⬆️</a></p>




<!-- GETTING STARTED -->
## 📋 Getting Started

This project was given by a class teacher during my master degree.
I learned how to work with mindset of TDD (Test driven development), and clean code based on the book.

You can find more information about TDD here : https://fr.wikipedia.org/wiki/Test_driven_development

Clean code book by Richard C. Martin : https://www.oreilly.com/library/view/clean-code-a/9780136083238/

### 🗺️ Prerequisites

You need JDK 16 for running this project.

Get JDK at this url : https://openjdk.org/projects/jdk/16/

<p align="right"><a href="#top">⬆️</a></p>


<!-- USAGE EXAMPLES -->
## 💾 Usage

In this project you'll find 3 Class.

### Grid
Grid class contain 2 dimensions array represent by a board game, you can instance two types of Grid.

1. With column and row value and number of generation 
   * The board is initialed with random Cell Alive / Dead
2. Based on figure, check the file enumeration Figures for more information
    * The board is initialed with dead Cell

Also, you have 2 types of running game. First depend on numbers of generation and second stop when all cells are dead.

The main part of this class is scan. Scan over whole board and store new value in a new board and set old board with the new one. 


### Cell

Cell class is related about cells contain in board inside Grid class. You have 2 types of constructor.

1. You can choose if cell is alive or not
2. IsAlive parameter is chosen randomly

Rule method is based on this : 

* Each cell with one or no neighbors dies, as if by solitude.
* Each cell with four or more neighbors dies, as if by overpopulation.
* Each cell with two or three neighbors survives.
* Each cell with three neighbors becomes populated.

### Menu

Menu class part interact with user. You can choose over 5 options.

1. Random game, you'll ask chose column, row and number of generation.
2. Plane shape, the game start with the shape of <a href="https://fr.wikipedia.org/wiki/Planeur_(jeu_de_la_vie)">Plane</a>
3. Flashing shape, the game start with the shape of <a href="https://fr.wikipedia.org/wiki/Oscillateur_(automate_cellulaire)">Flashing</a>
4. While everyone die, you'll ask chose column and row. The game will never end unless there is no cell.
5. Quit the game

### App

You can start the app inside App class, in main function.

### Test

All the tests can be run in test folder. 
Each file test whole functionality for the class Grid and Cell.

<p align="right"><a href="#top">⬆️</a></p>


<!-- CONTRIBUTING -->
## 🔗 Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right"><a href="#top">⬆️</a></p>




<!-- LICENSE -->
## 📰 License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right"><a href="#top">⬆️</a></p>




<!-- CONTACT -->
## 📫 Contact

Reach me at : gauron.dorian.pro@gmail.com.

Project Link: [https://github.com/Michelprogram/21-planets.git](https://github.com/Michelprogram/21-planets.git)

<p align="right"><a href="#top">⬆️</a></p>




<!-- ACKNOWLEDGMENTS -->
## ⛱️ Acknowledgments

This space is a list to resources i found helpful and would like to give credit to.

* [Learn react](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjYzP_lpu76AhUCcxoKHRQ8ASMQtwJ6BAgLEAI&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DbMknfKXIFA8&usg=AOvVaw0OS9ehWkuAe9Ahhi7b69mt)

<p align="right"><a href="#top">⬆️</a></p>

<a href="https://github.com/othneildrew/Best-README-Template">Template inspired by othneildrew</a>