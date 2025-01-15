-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 15-01-2025 a las 13:07:30
-- Versión del servidor: 5.7.44
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gimnasio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupocontrata`
--

CREATE TABLE `grupocontrata` (
  `id` bigint(20) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_planesentrenamiento` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupocontrata`
--

INSERT INTO `grupocontrata` (`id`, `titulo`, `descripcion`, `id_usuario`, `id_planesentrenamiento`) VALUES
(1, 'Título aleatorio 0', 'Descripción aleatoria 0', 19, 5),
(2, 'Título aleatorio 1', 'Descripción aleatoria 1', 13, 7),
(3, 'Título aleatorio 2', 'Descripción aleatoria 2', 20, 5),
(4, 'Título aleatorio 3', 'Descripción aleatoria 3', 2, 10),
(5, 'Título aleatorio 4', 'Descripción aleatoria 4', 13, 10),
(6, 'Título aleatorio 5', 'Descripción aleatoria 5', 17, 5),
(7, 'Título aleatorio 6', 'Descripción aleatoria 6', 13, 5),
(8, 'Título aleatorio 7', 'Descripción aleatoria 7', 19, 10),
(9, 'Título aleatorio 8', 'Descripción aleatoria 8', 18, 3),
(10, 'Título aleatorio 9', 'Descripción aleatoria 9', 10, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planesentrenamiento`
--

CREATE TABLE `planesentrenamiento` (
  `id` bigint(20) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `planesentrenamiento`
--

INSERT INTO `planesentrenamiento` (`id`, `titulo`, `descripcion`) VALUES
(1, 'Plan de entrenamiento 0', 'Descripción del plan de entrenamiento 0'),
(2, 'Plan de entrenamiento 1', 'Descripción del plan de entrenamiento 1'),
(3, 'Plan de entrenamiento 2', 'Descripción del plan de entrenamiento 2'),
(4, 'Plan de entrenamiento 3', 'Descripción del plan de entrenamiento 3'),
(5, 'Plan de entrenamiento 4', 'Descripción del plan de entrenamiento 4'),
(6, 'Plan de entrenamiento 5', 'Descripción del plan de entrenamiento 5'),
(7, 'Plan de entrenamiento 6', 'Descripción del plan de entrenamiento 6'),
(8, 'Plan de entrenamiento 7', 'Descripción del plan de entrenamiento 7'),
(9, 'Plan de entrenamiento 8', 'Descripción del plan de entrenamiento 8'),
(10, 'Plan de entrenamiento 9', 'Descripción del plan de entrenamiento 9');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` bigint(20) NOT NULL,
  `titulo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `titulo`) VALUES
(1, 'Administrador'),
(2, 'Entrenador Personal'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `id_tipousuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `id_tipousuario`) VALUES
(1, 'Sara', 'Pérez', 'Gonzalez', 'emailSara2476@gmail.com', 3),
(2, 'Ana', 'Sancho', 'Pérez', 'emailAna2976@gmail.com', 2),
(3, 'Lorenzo', 'Sanchez', 'Lopez', 'emailLorenzo9024@gmail.com', 1),
(4, 'Laura', 'Sancho', 'Sancho', 'emailLaura4665@gmail.com', 2),
(5, 'Lucia', 'Moreno', 'Feliu', 'emailLucia5812@gmail.com', 2),
(6, 'Luis', 'Sanchez', 'Pérez', 'emailLuis4900@gmail.com', 1),
(7, 'Marta', 'Martinez', 'Gonzalez', 'emailMarta7117@gmail.com', 2),
(8, 'Rocio', 'Hermoso', 'Sancho', 'emailRocio7385@gmail.com', 1),
(9, 'Carmen', 'Gimenez', 'Gonzalez', 'emailCarmen9387@gmail.com', 1),
(10, 'Carmen', 'Escriche', 'Feliu', 'emailCarmen9198@gmail.com', 3),
(11, 'Pepe', 'Martinez', 'Escriche', 'emailPepe3653@gmail.com', 2),
(12, 'Ignacio', 'Moreno', 'Feliu', 'emailIgnacio2165@gmail.com', 1),
(13, 'Ana', 'Gomez', 'Sanchez', 'emailAna3087@gmail.com', 2),
(14, 'Lucia', 'Lopez', 'Vidal', 'emailLucia9756@gmail.com', 2),
(15, 'Ana', 'Gonzalez', 'Lopez', 'emailAna6749@gmail.com', 1),
(16, 'Sara', 'Gonzalez', 'Martinez', 'emailSara6488@gmail.com', 2),
(17, 'Marta', 'Gomez', 'Escriche', 'emailMarta4726@gmail.com', 2),
(18, 'Paco', 'Garcia', 'Gomez', 'emailPaco5977@gmail.com', 1),
(19, 'Paco', 'Moreno', 'Gimenez', 'emailPaco8080@gmail.com', 2),
(20, 'Rafa', 'Martinez', 'Sancho', 'emailRafa4504@gmail.com', 1),
(21, 'Paco', 'Moreno', 'Pérez', 'emailPaco8056@gmail.com', 2),
(22, 'Pepe', 'Martinez', 'Pérez', 'emailPepe2471@gmail.com', 3),
(23, 'Pepe', 'Escriche', 'Sanchez', 'emailPepe7064@gmail.com', 1),
(24, 'Carmen', 'Sancho', 'Gimenez', 'emailCarmen2762@gmail.com', 2),
(25, 'Marta', 'Vidal', 'Pérez', 'emailMarta6579@gmail.com', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `grupocontrata`
--
ALTER TABLE `grupocontrata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_planesentrenamiento` (`id_planesentrenamiento`);

--
-- Indices de la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_tipousuario` (`id_tipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `grupocontrata`
--
ALTER TABLE `grupocontrata`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `grupocontrata`
--
ALTER TABLE `grupocontrata`
  ADD CONSTRAINT `grupocontrata_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `grupocontrata_ibfk_2` FOREIGN KEY (`id_planesentrenamiento`) REFERENCES `planesentrenamiento` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipousuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
