Feature: Busqueda cancion en YouTube
    @SearchSong
    Scenario: Realizar la busqueda de una cancion
        Given que se ingresa en la pagina principal de youtube
        When se realiza la busqueda de la cancion "interpol desire"
        Then validamos que la cancion seleccionada es la correcta