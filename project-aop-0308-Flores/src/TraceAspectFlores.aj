

public aspect TraceAspectFlores 
{
	pointcut classToTrace(): within(ComponentApp) || within(DataApp) || within(ServiceApp);
	pointcut methodToTrace(): classToTrace() && execution(String getName());
	
	before(): methodToTrace()
	{
		String info = thisJoinPointStaticPart.getSignature() + ", "
				+ thisJoinPointStaticPart.getSourceLocation().getLine();
		System.out.println("\t-->" + info);
	}
	
	after() : methodToTrace()
	{
		String info = thisJoinPointStaticPart.getSourceLocation().getFileName();
		System.out.println("\t<--" + info);
	}
}
