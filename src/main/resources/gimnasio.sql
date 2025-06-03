-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 03-06-2025 a las 17:03:52
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
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_planesentrenamiento` bigint(20) DEFAULT NULL,
  `creado_en` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupocontrata`
--

INSERT INTO `grupocontrata` (`id`, `id_usuario`, `id_planesentrenamiento`, `creado_en`) VALUES
(1, 19, 5, '2025-05-05'),
(2, 13, 7, '2025-05-06'),
(3, 20, 5, '2025-05-07'),
(4, 8, 3, '2025-05-08'),
(5, 13, 10, '2025-05-09'),
(6, 17, 5, '2025-05-10'),
(7, 13, 5, '2025-05-11'),
(8, 19, 10, '2025-05-12'),
(9, 18, 3, '2025-05-13'),
(10, 10, 3, '2025-05-14'),
(11, 2, 3, '2025-05-15'),
(12, 5, 3, '2025-05-16'),
(13, 2, 3, '2025-06-02'),
(14, 2, 1, '2025-06-02'),
(15, 2, 2, '2025-06-02'),
(16, 2, 3, '2025-06-02'),
(17, 2, 3, '2025-06-02'),
(18, 2, 5, '2025-06-02'),
(19, 2, 6, '2025-06-02'),
(20, 2, 8, '2025-06-02'),
(21, 1, 2, '2025-06-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `id` bigint(20) NOT NULL,
  `emisor_id` bigint(20) DEFAULT NULL,
  `receptor_id` bigint(20) DEFAULT NULL,
  `contenido` text,
  `fecha_envio` timestamp NULL DEFAULT NULL,
  `leido` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planesentrenamiento`
--

CREATE TABLE `planesentrenamiento` (
  `id` bigint(20) NOT NULL,
  `dificultad` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text,
  `fecha_creacion` date DEFAULT NULL,
  `id_creador` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `planesentrenamiento`
--

INSERT INTO `planesentrenamiento` (`id`, `dificultad`, `titulo`, `descripcion`, `fecha_creacion`, `id_creador`) VALUES
(1, 'AVANZADO', 'Plan de entrenamiento 0', 'Descripción del plan de entrenamiento 0', '2025-05-16', 4),
(2, 'AVANZADO', 'Plan de entrenamiento 1', 'Descripción del plan de entrenamiento 1', '2025-05-16', 4),
(3, 'AVANZADO', 'Plan de entrenamiento 2', 'Descripción del plan de entrenamiento 2', '2025-05-16', 4),
(4, 'AVANZADO', 'Plan de entrenamiento 3', 'Descripción del plan de entrenamiento 3', '2025-05-16', 4),
(5, 'BASICO', 'Plan de entrenamiento 4', 'Descripción del plan de entrenamiento 4', '2025-05-16', 4),
(6, 'AVANZADO', 'Plan de entrenamiento 5', 'Descripción del plan de entrenamiento 5', '2025-05-16', 1),
(7, 'BASICO', 'Plan de entrenamiento 6', 'Descripción del plan de entrenamiento 6', '2025-05-16', 1),
(8, 'INTERMEDIO', 'Plan de entrenamiento 7', 'Descripción del plan de entrenamiento 7', '2025-05-16', 1),
(9, 'INTERMEDIO', 'Plan de entrenamiento 8', 'Descripción del plan de entrenamiento 8', '2025-05-16', 1),
(10, 'INTERMEDIO', 'Plan de entrenamiento 9', 'Descripción del plan de entrenamiento 9', '2025-05-16', 1),
(11, 'INTERMEDIO', 'titulo de prueba', 'prueba de plan de entrenamiento', '2025-05-16', 1),
(12, 'AVANZADO', 'Hola', 'prueba', '2025-05-17', 1),
(13, 'MEDIA', 'Plan de fuerza', 'Plan para aumentar masa muscul', '2025-05-11', 2),
(14, 'MEDIA', 'Plan de fuerza', 'Plan para aumentar masa muscular', '2025-05-18', 2),
(15, 'MEDIA', 'contrato', 'de prueba 18 de mayo ', '2025-05-18', 2),
(16, 'AVANZADO', 'contrato', 'prueba final', '2025-05-18', 2),
(17, 'BASICO', 'contrato', 'PRUEBA', '2025-05-18', 13),
(18, 'MEDIA', 'Plan de entrenamiento de pierna', 'Este es un entrenamiento de pierna', '2025-06-02', 2);

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
  `id_tipousuario` bigint(20) DEFAULT NULL,
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
(1, 'Sara', 'Pérez', 'Gonzalez', 'emailSara2476@gmail.com', 3, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111113', 'Cuenca', '46002', 'Calle Mayor 3', '1990-01-26', '12345678A'),
(2, 'Ana', 'Sancho', 'Pérez', 'emailAna2976@gmail.com', 2, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111112', 'Madrid', '28001', 'Avenida Sol 2', '1989-02-12', '23456789B'),
(3, 'Lorenzo', 'Sanchez', 'Lopez', 'nuevoemail@gmail.com', 1, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111113', 'Barcelona', '08001', 'Calle Luna 3', '1995-03-23', '34567890C'),
(4, 'Laura', 'Sancho', 'Sancho', 'emailLaura4665@gmail.com', 2, '', '600111114', 'Sevilla', '41001', 'Plaza Roja 4', '1987-04-04', '45678901D'),
(5, 'Lucia', 'Moreno', 'Feliu', 'emailLucia5812@gmail.com', 2, '', '600111115', 'Valencia', '46002', 'Calle Verde 5', '1992-05-05', '56789012E'),
(6, 'Luis', 'Sanchez', 'Pérez', 'emailLuis4900@gmail.com', 1, '', '600111116', 'Granada', '18001', 'Avenida Mar 6', '1985-06-06', '67890123F'),
(7, 'Marta', 'Martinez', 'Gonzalez', 'emailMarta7117@gmail.com', 2, '', '600111117', 'Bilbao', '48001', 'Calle Norte 7', '1991-07-07', '78901234G'),
(8, 'Rocio', 'Hermoso', 'Sancho', 'emailRocio7385@gmail.com', 1, '', '600111118', 'Zaragoza', '50001', 'Calle Sur 8', '1993-08-08', '89012345H'),
(9, 'Carmen', 'Gimenez', 'Gonzalez', 'emailCarmen9387@gmail.com', 1, '', '600111119', 'Toledo', '45001', 'Paseo Oeste 9', '1988-09-09', '90123456I'),
(10, 'Carmen', 'Escriche', 'Feliu', 'emailCarmen9198@gmail.com', 3, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111120', 'Valencia', '46003', 'Calle Este 10', '1996-10-10', '01234567J'),
(11, 'Pepe', 'Martinez', 'Escriche', 'emailPepe3653@gmail.com', 2, '', '600111121', 'Murcia', '30001', 'Calle Azul 11', '1994-11-11', '11223344K'),
(12, 'Ignacio', 'Moreno', 'Feliu', 'emailIgnacio2165@gmail.com', 1, '', '600111122', 'Alicante', '03001', 'Av. Blanca 12', '1986-12-12', '22334455L'),
(13, 'Ana', 'Gomez', 'Sanchez', 'emailAna3087@gmail.com', 2, '7e4b4f5529e084ecafb996c891cfbd5b5284f5b00dc155c37bbb62a9f161a72e', '600111123', 'Castellón', '12001', 'Calle Violeta 13', '1997-01-13', '33445566M'),
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
-- Indices de la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_planesentrenamiento_usuario` (`id_creador`);

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
  ADD KEY `id_tipousuario` (`id_tipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `grupocontrata`
--
ALTER TABLE `grupocontrata`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

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
-- Filtros para la tabla `planesentrenamiento`
--
ALTER TABLE `planesentrenamiento`
  ADD CONSTRAINT `fk_planesentrenamiento_usuario` FOREIGN KEY (`id_creador`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipousuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
