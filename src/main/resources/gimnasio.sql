-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql-ramboot
-- Tiempo de generación: 17-05-2025 a las 11:16:17
-- Versión del servidor: 9.2.0
-- Versión de PHP: 8.2.27

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
  `id` bigint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `id_planesentrenamiento` bigint DEFAULT NULL,
  `creado_en` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupocontrata`
--

INSERT INTO `grupocontrata` (`id`, `titulo`, `descripcion`, `id_usuario`, `id_planesentrenamiento`, `creado_en`) VALUES
(1, 'Título aleatorio 0', 'Descripción aleatoria 0', 19, 5, NULL),
(2, 'Título aleatorio 1', 'Descripción aleatoria 1', 13, 7, NULL),
(3, 'Título aleatorio 2', 'Descripción aleatoria 2', 20, 5, NULL),
(4, 'Título aleatorio 3', 'Descripción aleatoria 3', 2, 10, NULL),
(5, 'Título aleatorio 4', 'Descripción aleatoria 4', 13, 10, NULL),
(6, 'Título aleatorio 5', 'Descripción aleatoria 5', 17, 5, NULL),
(7, 'Título aleatorio 6', 'Descripción aleatoria 6', 13, 5, NULL),
(8, 'Título aleatorio 7', 'Descripción aleatoria 7', 19, 10, NULL),
(9, 'Título aleatorio 8', 'Descripción aleatoria 8', 18, 3, NULL),
(10, 'Título aleatorio 9', 'Descripción aleatoria 9', 10, 3, NULL),
(11, 'gggg', 'gggg', 4, 3, NULL),
(12, 'prueba', 'de contrato', 5, 3, '2025-05-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `id` bigint NOT NULL,
  `emisor_id` bigint DEFAULT NULL,
  `receptor_id` bigint DEFAULT NULL,
  `contenido` text,
  `fecha_envio` timestamp NULL DEFAULT NULL,
  `leido` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planesentrenamiento`
--

CREATE TABLE `planesentrenamiento` (
  `id` bigint NOT NULL,
  `dificultad` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text,
  `fecha_creacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `planesentrenamiento`
--

INSERT INTO `planesentrenamiento` (`id`, `dificultad`, `titulo`, `descripcion`, `fecha_creacion`) VALUES
(1, '', 'Plan de entrenamiento 0', 'Descripción del plan de entrenamiento 0', NULL),
(2, '', 'Plan de entrenamiento 1', 'Descripción del plan de entrenamiento 1', NULL),
(3, '', 'Plan de entrenamiento 2', 'Descripción del plan de entrenamiento 2', NULL),
(4, '', 'Plan de entrenamiento 3', 'Descripción del plan de entrenamiento 3', NULL),
(5, '', 'Plan de entrenamiento 4', 'Descripción del plan de entrenamiento 4', NULL),
(6, '', 'Plan de entrenamiento 5', 'Descripción del plan de entrenamiento 5', NULL),
(7, '', 'Plan de entrenamiento 6', 'Descripción del plan de entrenamiento 6', NULL),
(8, '', 'Plan de entrenamiento 7', 'Descripción del plan de entrenamiento 7', NULL),
(9, '', 'Plan de entrenamiento 8', 'Descripción del plan de entrenamiento 8', NULL),
(10, '', 'Plan de entrenamiento 9', 'Descripción del plan de entrenamiento 9', NULL),
(11, '', 'titulo de prueba', 'prueba de plan de entrenamiento', '2025-05-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` bigint NOT NULL,
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
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `id_tipousuario` bigint DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `provincia` varchar(100) DEFAULT NULL,
  `codigo_postal` varchar(10) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `dni` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `id_tipousuario`, `password`, `telefono`, `provincia`, `codigo_postal`, `direccion`, `fecha_nacimiento`, `dni`) VALUES
(1, 'Sara', 'Pérez', 'Gonzalez', 'emailSara2476@gmail.com', 3, '', '600111112', 'Madrid', '46002', 'Calle Mayor 2', '1990-01-01', '12343678A'),
(2, 'Ana', 'Sancho', 'Pérez', 'emailAna2976@gmail.com', 2, '', '600111112', 'Madrid', '28001', 'Avenida Sol 2', '1989-02-12', '23456789B'),
(3, 'Lorenzo', 'Sanchez', 'Lopez', 'nuevoemail@gmail.com', 1, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111113', 'Barcelona', '08001', 'Calle Luna 3', '1995-03-23', '34567890C'),
(4, 'Laura', 'Sancho', 'Sancho', 'emailLaura4665@gmail.com', 2, '', '600111114', 'Sevilla', '41001', 'Plaza Roja 4', '1987-04-04', '45678901D'),
(5, 'Lucia', 'Moreno', 'Feliu', 'emailLucia5812@gmail.com', 2, '', '600111115', 'Valencia', '46002', 'Calle Verde 5', '1992-05-05', '56789012E'),
(6, 'Luis', 'Sanchez', 'Pérez', 'emailLuis4900@gmail.com', 1, '', '600111116', 'Granada', '18001', 'Avenida Mar 6', '1985-06-06', '67890123F'),
(7, 'Marta', 'Martinez', 'Gonzalez', 'emailMarta7117@gmail.com', 2, '', '600111117', 'Bilbao', '48001', 'Calle Norte 7', '1991-07-07', '78901234G'),
(8, 'Rocio', 'Hermoso', 'Sancho', 'emailRocio7385@gmail.com', 1, '', '600111118', 'Zaragoza', '50001', 'Calle Sur 8', '1993-08-08', '89012345H'),
(9, 'Carmen', 'Gimenez', 'Gonzalez', 'emailCarmen9387@gmail.com', 1, '', '600111119', 'Toledo', '45001', 'Paseo Oeste 9', '1988-09-09', '90123456I'),
(10, 'Carmen', 'Escriche', 'Feliu', 'emailCarmen9198@gmail.com', 3, '', '600111120', 'Valencia', '46003', 'Calle Este 10', '1996-10-10', '01234567J'),
(11, 'Pepe', 'Martinez', 'Escriche', 'emailPepe3653@gmail.com', 2, '', '600111121', 'Murcia', '30001', 'Calle Azul 11', '1994-11-11', '11223344K'),
(12, 'Ignacio', 'Moreno', 'Feliu', 'emailIgnacio2165@gmail.com', 1, '', '600111122', 'Alicante', '03001', 'Av. Blanca 12', '1986-12-12', '22334455L'),
(13, 'Ana', 'Gomez', 'Sanchez', 'emailAna3087@gmail.com', 2, '', '600111123', 'Castellón', '12001', 'Calle Violeta 13', '1997-01-13', '33445566M'),
(14, 'Lucia', 'Lopez', 'Vidal', 'emailLucia9756@gmail.com', 2, '', '600111124', 'Málaga', '29001', 'Paseo Largo 14', '1990-02-14', '44556677N'),
(15, 'Ana', 'Gonzalez', 'Lopez', 'emailAna6749@gmail.com', 1, '', '600111125', 'Huelva', '21001', 'Av. Media 15', '1989-03-15', '55667788O'),
(16, 'Sara', 'Gonzalez', 'Martinez', 'emailSara6488@gmail.com', 2, '', '600111126', 'Cádiz', '11001', 'Calle Alta 16', '1993-04-16', '66778899P'),
(17, 'Marta', 'Gomez', 'Escriche', 'emailMarta4726@gmail.com', 2, '', '600111127', 'La Rioja', '26001', 'Plaza Nueva 17', '1984-05-17', '77889900Q'),
(18, 'Paco', 'Garcia', 'Gomez', 'emailPaco5977@gmail.com', 1, '', '600111128', 'Salamanca', '37001', 'Av. Castilla 18', '1991-06-18', '88990011R'),
(19, 'Paco', 'Moreno', 'Gimenez', 'emailPaco8080@gmail.com', 2, '', '600111129', 'Burgos', '09001', 'Calle del Sol 19', '1992-07-19', '99001122S'),
(20, 'Rafa', 'Martinez', 'Sancho', 'emailRafa4504@gmail.com', 1, '', '600111130', 'Valladolid', '47001', 'Paseo Río 20', '1985-08-20', '10111223T'),
(21, 'Paco', 'Moreno', 'Pérez', 'emailPaco8056@gmail.com', 2, '', '600111131', 'Teruel', '44001', 'Calle Campo 21', '1990-09-21', '12131425U'),
(22, 'Pepe', 'Martinez', 'Pérez', 'emailPepe2471@gmail.com', 3, '', '600111132', 'Cuenca', '16001', 'Av. Río 22', '1986-10-22', '13141526V'),
(23, 'Pepe', 'Escriche', 'Sanchez', 'emailPepe7064@gmail.com', 1, '', '600111133', 'Albacete', '02001', 'Calle Jardín 23', '1994-11-23', '14151627W'),
(24, 'Carmen', 'Sancho', 'Gimenez', 'emailCarmen2762@gmail.com', 2, '', '600111134', 'Segovia', '40001', 'Paseo Bosque 24', '1993-12-24', '15161728X'),
(25, 'Marta', 'Vidal', 'Pérez', 'emailMarta6579@gmail.com', 1, '', '600111135', 'León', '24001', 'Calle Lago 25', '1988-01-25', '16171829Y'),
(26, 'Sara', 'Moreno', 'prueba', 'emaildeprueba3@gmail.cpm', 3, 'fef3d83e32b4d981b0c0f75206e891268c6aa8bd8db5a315db7bf24168a4be27', '123456789', 'Valencia', '12345', 'Calle Mayor 2', '2000-05-11', '12343678A'),
(27, 'Juan', 'cabrona', 'Gimenez', 'emailAna6828@gmail.com', 3, '07ff0accdf855eefc0a1b23c261ea852e7cfbe16ff006e5d121cd539b572ec3a', '123456789', 'Madrid', '54321', 'Calle Mayor 3', '1990-08-24', '23837793Q'),
(28, 'Juan', 'deee', 'gggggggggggggggggggggggg', 'emailSara2473@gmail.com', 2, 'ed92e4c7e54e3f4a29d8041ec93124bd3c1ec4825701cac42b3fffaf0bd7120a', '123456789', 'Madrid', '45445', 'Calle Mayor 50', '1997-07-04', '12345678A');

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
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_emisor` (`emisor_id`),
  ADD KEY `fk_receptor` (`receptor_id`);

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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

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
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `fk_emisor` FOREIGN KEY (`emisor_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_receptor` FOREIGN KEY (`receptor_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipousuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
