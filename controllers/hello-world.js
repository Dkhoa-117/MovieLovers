const asyncWrapper = require("../middlewares/async").default;
// ? for testing error handler
const { BadRequestError } = require("../errors");
module.exports = {
	helloWorld: asyncWrapper((req, res) => {
		// ? for testing error handler
		throw new BadRequestError("bug bug 🔴");
		res.status(200).json({
			message: "hello world",
		});
	}),
};
