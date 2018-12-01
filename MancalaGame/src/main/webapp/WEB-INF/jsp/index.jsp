<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" > </a>
			</div>
		</div>
	</nav>

	<div class="row centered">
		<div class="container">
			<h1>Game</h1>
			<h3>{ ${currentPlayer} } is your turn</h3>
		</div>

		<div class="container">
			<h2>{ ${showBoard[0]} } [ ${showBoard[1]} ] [ ${showBoard[2]} ]
				[ ${showBoard[3]} ] [ ${showBoard[4]} ] [ ${showBoard[5]} ] [
				${showBoard[6]} ]</h2>

			<h2>[ ${showBoard[13]} ] [ ${showBoard[12]} ] [ ${showBoard[11]}
				] [ ${showBoard[10]} ] [ ${showBoard[9]} ] [ ${showBoard[8]} ] {
				${showBoard[7] } }</h2>
		</div>

		<br>
		<br>
		<div class="container">
			<form method="post" action="result">
				<p >
					Enter the Pit Position: <input type="text" name="pitPosition" />
				</p>
				<p>
					<input class="btn btn-info" type="submit" value="Play" /> 
					<input class="btn btn-info" type="reset" value="Reset" />
				</p>

				<p>${message}</p>
			</form>
		</div>
	</div>
</body>

</html>