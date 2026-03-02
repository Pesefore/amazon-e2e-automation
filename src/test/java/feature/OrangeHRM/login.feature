@LoginExitoso
Feature: Login en OrangeHRM

  Scenario Outline: Login con credenciales válidas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa sesion con "<username>" y "<password>" validos
    Then se redirecciona su perfil de usuario
    Examples:
    |username|password|
    |Admin   |admin123|

@LoginNoExitoso
    Scenario Outline: Login con credencias inválidas
      Given el usuario se encuentra en la pagina de inicio de sesion
      When ingresa sesion "<username>" y "<password>" invalidos
      Then vuelve a ingresar nuevamente sus credenciales
      Examples:
      |username|password|
      |Admin|contraseñaMALA|

