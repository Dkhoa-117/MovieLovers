const asyncWrapper = require("../middlewares/async").default;
const tokenGenerator = require("../utils/token-generator");
const { BadRequestError } = require("../errors");

module.exports = {
	login: asyncWrapper((req, res) => {
		const { username, password } = req.body;

		if (!(username && password)) {
			throw new BadRequestError("Please provide username and password");
		}
		//just for demo, normally provided by DB!!!!
		const id = new Date().getDate();
		const token = tokenGenerator.generateToken(id, username);
		res.status(200).json({ message: "You're Signed in", token });
	}),
	register: asyncWrapper((req, res) => {
		const { username, password, status } = req.body;
		//check data
		if (!username || !password) {
			throw new BadRequestError("Please provide email and password");
		}
		//save data

		//just for demo, normally provided by DB!!!!
		const id = new Date().getDate();

		const token = generateToken(id, username);

		res.status(200).json({ message: "user created", token });
	}),
	dashboard: asyncWrapper((req, res) => {
		const luckyNumber = Math.floor(Math.random() * 100);

		res.status(200).json({
			message: `Hello, ${req.user.username}`,
			secret: `Here is your authorized data, your lucky number is ${luckyNumber}`,
		});
	}),
};
