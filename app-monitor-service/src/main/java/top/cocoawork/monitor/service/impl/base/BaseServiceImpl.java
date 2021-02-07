package top.cocoawork.monitor.service.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.common.BeanUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author cocoawork
 */
abstract public class BaseServiceImpl<D, DTO> {

    private final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private Class<D> dClass = getClass(0);
    private Class<DTO> dtoClass = getClass(1);


    public void d2dto(D d, DTO dto) throws ServiceException {
        try {
            BeanUtil.copyProperties(d, dto);
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void dto2d(DTO dto, D d) throws ServiceException {
        try {
            BeanUtil.copyProperties(dto, d);
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public DTO d2dto(D d) throws ServiceException {
        try {
            DTO dto = dtoClass.newInstance();
            d2dto(d, dto);
            return dto;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServiceException(e);
        }
    }

    public D dto2d(DTO dto) throws ServiceException {
        try {
            D d = dClass.newInstance();
            dto2d(dto, d);
            return d;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServiceException(e);
        }
    }


    private Class getClass(int index) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType)superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length >= (1+index) ) {
                return (Class) types[index];
            }
        }
        return null;
    }

}
